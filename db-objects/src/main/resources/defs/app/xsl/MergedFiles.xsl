<?xml version="1.1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:merge="http://simpleSys.ru/xml/library/merge" xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:common="http://simpleSys.ru/xml/library/common" exclude-result-prefixes="xs common">

    <!--<xsl:import href="common.xsl"/>-->
    <xsl:output indent="yes" method="xml" encoding="UTF-8" byte-order-mark="no" name="format"/>

    <!-- Normalize the contents of text, comment, and processing-instruction
   nodes before comparing?
   Default: yes -->
    <xsl:param name="normalize" as="xs:boolean" select="true()"/>

    <xsl:param name="merge-attribute" as="xs:boolean" select="true()"/>

    <!-- Don't merge elements with this (qualified) name -->
    <xsl:param name="dontmerge"/>

    <!-- If set to true, text nodes in file1 will be replaced -->
    <xsl:param name="replace" as="xs:boolean" select="true()"/>
    <!--<xsl:param name="fileRes" as="xs:string" select="'/tmp/user.12.xml'"/>-->

    <xsl:template name="merge:checkIncomingFiles">
        <xsl:param as="xs:string" name="file1" required="yes"/>
        <xsl:param as="xs:string" name="file2" required="yes"/>

        <xsl:choose>
            <xsl:when test="$file1=''">
                <xsl:message terminate="yes">
                    <xsl:value-of select="'Не определен file1'"/>
                </xsl:message>
            </xsl:when>
            <xsl:when test="$file2=''">
                <xsl:message terminate="yes">
                    <xsl:value-of select="'Не определен file2'"/>
                </xsl:message>
            </xsl:when>
            <xsl:otherwise>
                <!--<xsl:message>
                    <xsl:value-of select="concat('file1 = ',common:dblQuoted($file1))"/>
                </xsl:message>
                <xsl:message>
                    <xsl:value-of select="concat('file2 = ',common:dblQuoted($file2))"/>
                </xsl:message>-->
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="merge:Files">
        <xsl:variable as="xs:string" name="file1" select="/merge:Files/merge:Group/merge:File[merge:Order = 1]/merge:Name"/>
        <xsl:variable as="xs:string" name="file2" select="/merge:Files/merge:Group/merge:File[merge:Order = 2]/merge:Name"/>

        <xsl:call-template name="merge:checkIncomingFiles">
            <xsl:with-param name="file1" select="$file1"/>
            <xsl:with-param name="file2" select="$file2"/>
        </xsl:call-template>

        <xsl:call-template name="merge:Merge">
            <xsl:with-param name="nodes1" select="doc($file1)/node()"/>
            <xsl:with-param name="nodes2" select="doc($file2)/node()"/>
        </xsl:call-template>
    </xsl:template>    

    <!-- The "merge" template -->
    <xsl:template name="merge:Merge">
        <xsl:param as="node()*" name="nodes1" required="yes"/>
        <xsl:param as="node()*" name="nodes2" required="yes"/>

        <xsl:choose>
            <!-- Is $nodes1 resp. $nodes2 empty? -->
            <xsl:when test="count($nodes1)=0">
                <xsl:copy-of select="$nodes2"/>
            </xsl:when>
            <xsl:when test="count($nodes2)=0">
                <xsl:copy-of select="$nodes1"/>
            </xsl:when>
            <xsl:otherwise>
                <!-- Split $nodes1 and $nodes2 -->
                <xsl:variable name="head1" select="$nodes1[1]"/>
                <xsl:variable name="tail1" select="$nodes1[position()!=1]"/>
                <xsl:variable name="head2" select="$nodes2[1]"/>
                <xsl:variable name="tail2" select="$nodes2[position()!=1]"/>
                <!-- Determine type of node $head1 -->
                <xsl:variable name="type1">
                    <xsl:apply-templates mode="merge:detect-type" select="$head1"/>
                </xsl:variable>

                <xsl:choose>
                    <!-- $head1 != $head2 -->
                    <xsl:when test="merge:compare-nodes($head1, $head2)='!'">
                        <!-- Compare $head1 and $tail2 -->
                        <xsl:variable name="diff-rest">
                            <xsl:for-each select="$tail2">
                                <xsl:value-of select="merge:compare-nodes($head1, .)"/>
                            </xsl:for-each>
                        </xsl:variable>

                        <xsl:choose>
                            <!-- $head1 is in $tail2 and $head1 is *not* an empty text node  -->
                            <xsl:when test="contains($diff-rest,'=') and not($type1='text' and normalize-space($head1)='')">
                                <!-- determine position of $head1 in $nodes2 and copy all preceding nodes of $nodes2 -->
                                <xsl:variable name="pos" select="string-length(substring-before($diff-rest,'=')) + 2"/>
                                <xsl:copy-of select="$nodes2[position() &lt; $pos]"/>
                                <!-- merge $head1 with its equivalent node -->
                                <xsl:choose>
                                    <!-- Elements: merge -->
                                    <xsl:when test="$type1='element'">
                                        <xsl:element name="{name($head1)}" namespace="{namespace-uri($head1)}">
                                            <xsl:copy-of select="$head1/namespace::*"/>
                                            <xsl:copy-of select="$head2/namespace::*"/>
                                            <xsl:copy-of select="$head1/@*"/>
                                            <xsl:call-template name="merge:Merge">
                                                <xsl:with-param name="nodes1" select="$head1/node()"/>
                                                <xsl:with-param name="nodes2" select="$nodes2[position()=$pos]/node()"/>
                                            </xsl:call-template>
                                        </xsl:element>
                                    </xsl:when>
                                    <!-- Other: copy -->
                                    <xsl:otherwise>
                                        <xsl:copy-of select="$head1"/>
                                    </xsl:otherwise>
                                </xsl:choose>

                                <!-- Merge $tail1 and rest of $nodes2 -->
                                <xsl:call-template name="merge:Merge">
                                    <xsl:with-param name="nodes1" select="$tail1"/>
                                    <xsl:with-param name="nodes2" select="$nodes2[position() &gt; $pos]"/>
                                </xsl:call-template>
                            </xsl:when>

                            <!-- $head1 is a text node and replace mode was activated -->
                            <xsl:when test="$type1='text' and $replace">
                                <xsl:call-template name="merge:Merge">
                                    <xsl:with-param name="nodes1" select="$tail1"/>
                                    <xsl:with-param name="nodes2" select="$nodes2"/>
                                </xsl:call-template>
                            </xsl:when>

                            <!-- else: $head1 is not in $tail2 or $head1 is an empty text node -->
                            <xsl:otherwise>
                                <xsl:copy-of select="$head1"/>
                                <xsl:call-template name="merge:Merge">
                                    <xsl:with-param name="nodes1" select="$tail1"/>
                                    <xsl:with-param name="nodes2" select="$nodes2"/>
                                </xsl:call-template>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:when>

                    <!-- else: $head1 = $head2 -->
                    <xsl:otherwise>
                        <xsl:choose>
                            <!-- Elements: merge -->
                            <xsl:when test="$type1='element'">
                                <xsl:element name="{name($head1)}" namespace="{namespace-uri($head1)}">
                                    <xsl:copy-of select="$head1/namespace::*"/>
                                    <xsl:copy-of select="$head2/namespace::*"/>
                                    <xsl:copy-of select="$head1/@*"/>
                                    <xsl:if test="$merge-attribute">
                                        <xsl:copy-of select="$head2/@*"/>
                                    </xsl:if>
                                    <xsl:call-template name="merge:Merge">
                                        <xsl:with-param name="nodes1" select="$head1/node()"/>
                                        <xsl:with-param name="nodes2" select="$head2/node()"/>
                                    </xsl:call-template>
                                </xsl:element>
                            </xsl:when>
                            <!-- Other: copy -->
                            <xsl:otherwise>
                                <xsl:copy-of select="$head1"/>
                            </xsl:otherwise>
                        </xsl:choose>

                        <!-- Merge $tail1 and $tail2 -->
                        <xsl:call-template name="merge:Merge">
                            <xsl:with-param name="nodes1" select="$tail1"/>
                            <xsl:with-param name="nodes2" select="$tail2"/>
                        </xsl:call-template>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template name="printAtributes">
        <xsl:param as="node()*" name="node"/>
        <xsl:message select="'-----------------------------------------------------------------------------------------------------------------------'"/>
        <xsl:for-each select="$node/@*">
            <xsl:message select="concat('local-name(): ', local-name())"/>
            <xsl:message select="concat('curent(): ', current())"/>
        </xsl:for-each>
        <xsl:message select="'-----------------------------------------------------------------------------------------------------------------------'"/>
    </xsl:template>

    <!-- Comparing single nodes:
   if $node1 and $node2 are equivalent then the template creates a
   text node "=" otherwise a text node "!" -->
    <xsl:function name="merge:compare-nodes" as="xs:string">
        <xsl:param as="node()*" name="node1"/>
        <xsl:param as="node()*" name="node2"/>

        <xsl:variable name="type1">
            <xsl:apply-templates mode="merge:detect-type" select="$node1"/>
        </xsl:variable>

        <xsl:variable name="type2">
            <xsl:apply-templates mode="merge:detect-type" select="$node2"/>
        </xsl:variable>

        <xsl:choose>
            <!-- Are $node1 and $node2 element nodes with the same name? -->
            <xsl:when test="$type1='element' and $type2='element' and local-name($node1)=local-name($node2) and namespace-uri($node1)=namespace-uri($node2) and name($node1)!=$dontmerge and name($node2)!=$dontmerge">
                <!-- Comparing the attributes -->
                <!--<xsl:call-template name="printAtributes">
                    <xsl:with-param name="node" select="$node1"/>
                </xsl:call-template>-->
                <xsl:variable name="diff-att">
                    <xsl:choose>
                        <xsl:when test="$node1/@key4MergeValue and $node2/@key4MergeValue and $merge-attribute">
                            <xsl:if test="not($node1/@key4MergeValue = $node2/@key4MergeValue)">.</xsl:if>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:choose>
                                <!-- same number ... -->
                                <xsl:when test="count($node1/@*)!=count($node2/@*)">.</xsl:when>
                                <xsl:otherwise>
                                    <!-- ... and same name/content -->
                                    <xsl:for-each select="$node1/@*">
                                        <xsl:if test="not($node2/@* [local-name()=local-name(current()) and namespace-uri()=namespace-uri(current()) and .=current()])">.</xsl:if>
                                    </xsl:for-each>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:variable>
                <!--<xsl:message select="concat('local-name($node1):',local-name($node1),' diff-att: ',$diff-att)"/>-->
                <xsl:choose>
                    <xsl:when test="string-length($diff-att)!=0">!</xsl:when>
                    <xsl:otherwise>=</xsl:otherwise>
                </xsl:choose>
            </xsl:when>

            <!-- Other nodes: test for the same type and content -->
            <xsl:when test="$type1!='element' and $type1=$type2 and name($node1)=name($node2) and ($node1=$node2 or ($normalize and normalize-space($node1)= normalize-space($node2)))">=
      </xsl:when>

            <!-- Otherwise: different node types or different name/content -->
            <xsl:otherwise>!</xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <!-- Type detection, thanks to M. H. Kay -->
    <xsl:template match="*" mode="merge:detect-type">element</xsl:template>
    <xsl:template match="text()" mode="merge:detect-type">text</xsl:template>
    <xsl:template match="comment()" mode="merge:detect-type">comment</xsl:template>
    <xsl:template match="processing-instruction()" mode="merge:detect-type">pi</xsl:template>
</xsl:stylesheet>