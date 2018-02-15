<?xml version="1.1"?>
<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output encoding="UTF-8" indent="yes" method="html"/>

	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="greeting">
		<html>
			<body>
				<h1>
					<xsl:value-of select="."/>
				</h1>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="z">
		<z1>
			<xsl:apply-templates/>
		</z1>
	</xsl:template>
</xsl:stylesheet>