isc.DrawPane.create(
    {
        ID       : "DrawPane0",
        autoDraw : false,
        canFocus : true,
        height   : "100%",
        left     : 0,
        top      : 0,
        width    : "100%",
        drawItems: [
            isc.DrawOval.create(
                {
                    height              : 50,
                    left                : 15,
                    top                 : 13,
                    width               : 126,
                    lineWidth           : 1,
                    fillGradient        : "diamond",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Patient arrives",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawDiamond.create(
                {
                    height              : 50,
                    left                : 15,
                    top                 : 103,
                    width               : 124,
                    lineWidth           : 1,
                    fillGradient        : "diamond",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Patient in system?",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 14,
                    top                 : 202,
                    width               : 132,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Needs to complete\npaperwork?",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 211,
                    top                 : 200,
                    width               : 132,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Waiting room",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawDiamond.create(
                {
                    height              : 50,
                    left                : 208,
                    top                 : 105,
                    width               : 132,
                    lineWidth           : 1,
                    fillGradient        : "diamond",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Nurse available?",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 417,
                    top                 : 106,
                    width               : 136,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Take pulse, BP,\nweight, urine",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawDiamond.create(
                {
                    height              : 50,
                    left                : 412,
                    top                 : 199,
                    width               : 136,
                    lineWidth           : 1,
                    fillGradient        : "diamond",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Doctor available?",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 620,
                    top                 : 200,
                    width               : 136,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Patient with doctor",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 417,
                    top                 : 295,
                    width               : 136,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Waiting room",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawDiamond.create(
                {
                    height              : 50,
                    left                : 623,
                    top                 : 290,
                    width               : 134,
                    lineWidth           : 1,
                    fillGradient        : "diamond",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Needs follow-up?",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 831,
                    top                 : 293,
                    width               : 137,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Make an appointment",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawDiamond.create(
                {
                    height              : 50,
                    left                : 621,
                    top                 : 383,
                    width               : 140,
                    lineWidth           : 1,
                    fillGradient        : "diamond",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Needs medication?",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 834,
                    top                 : 386,
                    width               : 132,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Send to pharmacy",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawRect.create(
                {
                    height              : 50,
                    left                : 836,
                    top                 : 479,
                    width               : 126,
                    lineWidth           : 1,
                    fillGradient        : "rect",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Dispense medication",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        78.0,
                        66.0
                    ],
                    endPoint        : [
                        77.0,
                        100.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        77.0,
                        154.0
                    ],
                    endPoint            : [
                        77.0,
                        200.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "No",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        139.0,
                        131.0
                    ],
                    endPoint            : [
                        206.0,
                        131.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "Yes",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        335.0,
                        131.0
                    ],
                    endPoint            : [
                        416.0,
                        132.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "Yes",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        275.0,
                        158.0
                    ],
                    endPoint            : [
                        276.0,
                        198.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "No",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        156.0,
                        227.0
                    ],
                    endPoint        : [
                        154.0,
                        131.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        158.0,
                        229.0
                    ],
                    endPoint        : [
                        212.0,
                        230.0
                    ],
                    lineWidth       : 1,
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        480.0,
                        159.0
                    ],
                    endPoint        : [
                        481.0,
                        196.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        482.0,
                        251.0
                    ],
                    endPoint            : [
                        483.0,
                        292.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "No",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        549.0,
                        225.0
                    ],
                    endPoint            : [
                        619.0,
                        225.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "Yes",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        366.0,
                        330.0
                    ],
                    endPoint        : [
                        365.0,
                        224.0
                    ],
                    lineWidth       : 1,
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        365.0,
                        224.0
                    ],
                    endPoint        : [
                        410.0,
                        224.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        368.0,
                        329.0
                    ],
                    endPoint        : [
                        417.0,
                        329.0
                    ],
                    lineWidth       : 1,
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        691.0,
                        255.0
                    ],
                    endPoint        : [
                        691.0,
                        288.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        691.0,
                        344.0
                    ],
                    endPoint            : [
                        691.0,
                        382.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "No",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        756.0,
                        316.0
                    ],
                    endPoint            : [
                        831.0,
                        316.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "Yes",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawOval.create(
                {
                    height              : 50,
                    left                : 842,
                    top                 : 570,
                    width               : 124,
                    lineWidth           : 1,
                    fillGradient        : "oval",
                    shadow              : {
                        color : "#333333",
                        blur  : 2,
                        offset: [
                            1.0,
                            1.0
                        ]
                    },
                    keepInParentRect    : true,
                    title               : "Patient leaves",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        761.0,
                        409.0
                    ],
                    endPoint            : [
                        832.0,
                        409.0
                    ],
                    lineWidth           : 1,
                    endArrow            : "block",
                    keepInParentRect    : true,
                    title               : "Yes",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        696.0,
                        596.0
                    ],
                    endPoint        : [
                        842.0,
                        597.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint          : [
                        693.0,
                        435.0
                    ],
                    endPoint            : [
                        695.0,
                        596.0
                    ],
                    lineWidth           : 1,
                    keepInParentRect    : true,
                    title               : "No",
                    titleLabelProperties: {
                        lineColor: "#222222"
                    }
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        899.0,
                        439.0
                    ],
                    endPoint        : [
                        899.0,
                        476.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        901.0,
                        532.0
                    ],
                    endPoint        : [
                        901.0,
                        568.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        1016.0,
                        594.0
                    ],
                    endPoint        : [
                        967.0,
                        594.0
                    ],
                    lineWidth       : 1,
                    endArrow        : "block",
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        1014.0,
                        318.0
                    ],
                    endPoint        : [
                        1018.0,
                        596.0
                    ],
                    lineWidth       : 1,
                    keepInParentRect: true
                }
            )
            ,
            isc.DrawLine.create(
                {
                    startPoint      : [
                        969.0,
                        319.0
                    ],
                    endPoint        : [
                        1015.0,
                        319.0
                    ],
                    lineWidth       : 1,
                    keepInParentRect: true
                }
            )

        ],
        gradients: [
            {
                direction : 90.0,
                startColor: "#ffffff",
                endColor  : "#99ccff",
                id        : "oval"
            },
            {
                direction : 90.0,
                startColor: "#d3d3d3",
                endColor  : "#666699",
                id        : "diamond"
            },
            {
                direction : 90.0,
                startColor: "#f5f5f5",
                endColor  : "#a9b3b8",
                id        : "rect"
            },
            {
                direction : 90.0,
                startColor: "#f5f5f5",
                endColor  : "#667766",
                id        : "triangle"
            }
        ]
    }
)

