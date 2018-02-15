#isc.DrawItem.addProperties
#	_updateTitleLabelAndBackground: `function () {
#        if (isc.isA.Object(this.titleRotationMode))  this.titleRotationMode = this.titleRotationMode.toString()
#
#        var title = this.title,
#            titleLabel = this.titleLabel,
#            showTitleLabelBackground = this.showTitleLabelBackground,
#            titleLabelBackground = this.titleLabelBackground,
#            drawPane = this.drawPane;
#
#        // If the title is null or this DrawItem is not in a DrawPane, then the titleLabel should
#        // be hidden.
#        if (title == null || drawPane == null) {
#            if (titleLabelBackground != null && !titleLabelBackground.destroyed && titleLabelBackground.drawPane != null) {
#                titleLabelBackground.drawPane.removeDrawItem(titleLabelBackground);
#            }
#            if (titleLabel != null && !titleLabel.destroyed && titleLabel.drawPane != null) {
#                titleLabel.drawPane.removeDrawItem(titleLabel);
#                titleLabel.setContents(null); // clear the contents to potentially free up some memory
#            }
#
#            // Otherwise, the titleLabel should be shown.
#        } else {
#            // Create/add the titleLabelBackground first so that it is below the titleLabel in
#            // stacking order.
#            if (showTitleLabelBackground) {
#                if (titleLabelBackground == null || titleLabelBackground.destroyed) {
#                    titleLabelBackground = this.addPeer(
#                        this.createAutoChild(
#                            "titleLabelBackground", {
#                                autoDraw  : false,
#                                eventProxy: this,
#                                knobs     : null,
#                                title     : null,
#                                rotation  : initialRotation
#                            }
#                        ), "titleLabelBackground"
#                    );
#                } else if (titleLabelBackground.drawPane !== drawPane) {
#                    drawPane.addDrawItem(titleLabelBackground);
#                }
#            } else {
#                if (titleLabelBackground != null && !titleLabelBackground.destroyed && titleLabelBackground.drawPane != null) {
#                    titleLabelBackground.drawPane.removeDrawItem(titleLabelBackground);
#                }
#            }
#
#            var titleRotationMode = this.titleRotationMode || isc.DrawItem._instancePrototype.titleRotationMode,
#                initialRotation = null,
#                finalRotation = null,
#                isADrawLine = isc.isA.DrawLine(this),
#                isADrawLinePath = isc.isA.DrawLinePath(this),
#                isADrawLinePathSS = isc.isA.DrawLinePathSS(this),  //Доработки А.Юдин
#                flipped = false;
#
#            if (titleRotationMode !== "neverRotate") {
#                initialRotation = this.rotation;
#                if (initialRotation == null) {
#                    initialRotation = 0;
#                }
#
#                if ((isADrawLine || isADrawLinePath || isADrawLinePathSS) && //Доработки А.Юдин
#                    (titleRotationMode === "withLine" || titleRotationMode === "withLineAlwaysUp")) {
#                    var startLeft,
#                        startTop,
#                        endLeft,
#                        endTop;
#
#                    if (isADrawLinePathSS) {
#                        var segmentPoints = this._segmentPoints;
#
#                        if (segmentPoints.length <= 2) {
#                            startLeft = this.startLeft;
#                            startTop = this.startTop;
#                            endLeft = this.endLeft;
#                            endTop = this.endTop;
#                        } else {
#                            var centerSegmentStartPoint = segmentPoints[0],
#                                centerSegmentEndPoint = segmentPoints[1];
#                            startLeft = centerSegmentStartPoint[0];
#                            startTop = centerSegmentStartPoint[1];
#                            endLeft = centerSegmentEndPoint[0];
#                            endTop = centerSegmentEndPoint[1];
#                        }
#                    } else if (isADrawLinePath) {
#                        var segmentPoints = this._segmentPoints;
#
#                        var centerSegmentStartPoint = segmentPoints[1],
#                            centerSegmentEndPoint = segmentPoints[2];
#                        startLeft = centerSegmentStartPoint[0];
#                        startTop = centerSegmentStartPoint[1];
#                        endLeft = centerSegmentEndPoint[0];
#                        endTop = centerSegmentEndPoint[1];
#                    } else {
#
#                        startLeft = this.startLeft;
#                        startTop = this.startTop;
#                        endLeft = this.endLeft;
#                        endTop = this.endTop;
#                    }
#
#                    // finalRotation is the angle that the titleLabel should be rotated (in degrees) such
#                    // that it matches the line direction.
#                    //
#                    // To get the direction of the line, use atan2().
#                    // In drawing coordinates, the positive Y direction points downward, whereas atan2()
#                    // works in a coordinate system where the positive Y direction points upward.
#                    // Also, atan2() returns the angle where positive is counter-clockwise (drawing
#                    // treats the clockwise direction as positive).
#                    finalRotation = -Math.atan2(-(endTop - startTop), endLeft - startLeft) / this._radPerDeg + initialRotation;
#                    finalRotation = finalRotation % 360;
#
#                } else {
#                    finalRotation = initialRotation;
#                }
#
#                if (finalRotation < -180) finalRotation += 360;
#                else if (finalRotation > 180) finalRotation -= 360;
#
#                if (titleRotationMode === "withItemAlwaysUp" || titleRotationMode === "withLineAlwaysUp") {
#                    if (finalRotation > 90) {
#                        finalRotation -= 180;
#                        flipped = true;
#                    } else if (finalRotation < -90) {
#                        finalRotation += 180;
#                        flipped = true;
#                    }
#
#                }
#            }
#
#            var titleLabelPadding = this.titleLabelPadding,
#                twiceTitleLabelPadding = 2 * titleLabelPadding;
#
#            // If the titleLabel hasn't been created yet or was destroyed, (re-)create it now.
#            if (titleLabel == null || titleLabel.destroyed) {
#                if (!isc.isA.DrawPane(this.drawPane)) {
#                    return;
#                }
#
#                var titleLabelDynamicProps = {
#                    drawPane  : drawPane,
#                    autoDraw  : false,
#                    eventProxy: this,
#                    contents  : title,
#                    knobs     : null,
#                    title     : null,
#                    left      : null,
#                    top       : null,
#                    rotation  : finalRotation || initialRotation
#                };
#
#                var pi = this._calculateTitleLabelPositionInfo(
#                    title,
#                    isc.addProperties(
#                        {},
#                        this.titleLabelDefaults,
#                        this.titleLabelProperties,
#                        titleLabelDynamicProps
#                    ),
#                    drawPane
#                );
#
#                titleLabelDynamicProps.left = pi.topLeftPoint[0] + (this.drawingBitmap ? 1 : 0);
#                titleLabelDynamicProps.top = pi.topLeftPoint[1];
#                titleLabel = this.addPeer(this.createAutoChild("titleLabel", titleLabelDynamicProps), "titleLabel");
#
#                if (showTitleLabelBackground) {
#
#                    var dims = pi.dims;
#                    titleLabelBackground.setRect(
#                        isc.DrawItem._makeCoordinate(pi.topLeftPoint[0]) - titleLabelPadding,
#                        isc.DrawItem._makeCoordinate(pi.topLeftPoint[1]) - titleLabelPadding,
#                        dims.width + twiceTitleLabelPadding,
#                        dims.height + twiceTitleLabelPadding
#                    );
#                    if (finalRotation != null) {
#                        titleLabelBackground.rotateTo(finalRotation);
#                    }
#                }
#
#                // Otherwise, the titleLabel has been created.
#            } else {
#                // If the titleLabel is in a different DrawPane, then move it to this DrawItem's
#                // DrawPane.
#                if (titleLabel.drawPane !== drawPane) {
#                    drawPane.addDrawItem(titleLabel);
#
#                    // If the titleLabel is stacked below the titleLabelBackground, then re-add the titleLabel.
#                } else if (showTitleLabelBackground && titleLabel._addOrder < titleLabelBackground._addOrder) {
#                    drawPane.addDrawItem(titleLabel);
#                }
#
#                // Update the position of the titleLabel and titleLabelBackground.
#                var pi = this._calculateTitleLabelPositionInfo(title, titleLabel, drawPane),
#                    p;
#                if (initialRotation != null) {
#                    titleLabel.rotateTo(initialRotation);
#                    p = titleLabel._normalize(pi.topLeftPoint[0], pi.topLeftPoint[1], "drawing", "local");
#                } else {
#                    p = pi.topLeftPoint;
#                }
#
#                titleLabel._moveTo(
#                    isc.DrawItem._makeCoordinate(p[0]) + (this.drawingBitmap ? 1 : 0),
#                    isc.DrawItem._makeCoordinate(p[1])
#                );
#                titleLabel.setContents(title, true);
#                if (finalRotation != null) {
#                    titleLabel.rotateTo(finalRotation);
#                }
#                if (this.showTitleLabelBackground) {
#                    var titleLabelBackground = this.titleLabelBackground,
#                        dims = pi.dims;
#
#                    if (initialRotation != null) {
#                        titleLabelBackground.rotateTo(initialRotation);
#                        p = titleLabelBackground._normalize(pi.topLeftPoint[0], pi.topLeftPoint[1], "drawing", "local");
#                    } else {
#                        p = pi.topLeftPoint;
#                    }
#                    titleLabelBackground.setRect(
#                        isc.DrawItem._makeCoordinate(p[0]) - titleLabelPadding,
#                        isc.DrawItem._makeCoordinate(p[1]) - titleLabelPadding,
#                        dims.width + twiceTitleLabelPadding,
#                        dims.height + twiceTitleLabelPadding
#                    );
#                    if (finalRotation != null) {
#                        titleLabelBackground.rotateTo(finalRotation);
#                    }
#                }
#            }
#        }
#    }`
#
