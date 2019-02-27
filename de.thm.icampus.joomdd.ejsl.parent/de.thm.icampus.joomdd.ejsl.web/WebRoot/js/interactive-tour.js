require(["jquery"], function ($) {

    class TourPoint {
        get description() {
            return this._description;
        }

        get objects() {
            return this._objects;
        }

        constructor(objects, description) {
            this._objects = objects;
            this._description = description;
        }
    }

    class InteractiveTour {

        /* TODO: Add tour points accordingly */
        tourPoints = [
            new TourPoint($('#saveModel'), `This is the save button. Press this button to save your model.`),
            new TourPoint($('#generateCode'), `This is the generate button. Use this button to start the 
            generation of your excellent MDD'd Joomla! component`)
        ];
        position = 0;
        isActive = false;

        createPagination() {
            const paginationNav = $(`<nav aria-label="Page navigation example"></nav>`);
            const paginationList = $(`<ul class="pagination justify-content-center"></ul>`);
            const previousButton = $(`<li class="page-item">
                                          <a class="page-link" tabindex="-1" href="#">Previous</a>
                                        </li>`);
            const nextButton = $(`<li class="page-item">
                                      <a class="page-link" href="#">Next</a>
                                    </li>`);
            const stopButton = $(`<li class="page-item">
                                      <a class="page-link" href="#">Stop tour</a>
                                    </li>`);

            previousButton.on('click', (event) => {
                event.stopPropagation();
                this.previousTourPoint();
            });
            nextButton.on('click', (event) => {
                event.stopPropagation();
                this.nextTourPoint();
            });
            stopButton.on('click', (event) => {
                event.stopPropagation();
                this.endTour();
            });

            paginationList.append(previousButton);
            this.tourPoints.forEach((tourPoint, index) => {
                const listEntry = $(`<li class="page-item"><a class="page-link" href="#">${index + 1}</a></li>`);
                listEntry.on('click', (event) => {
                    event.stopPropagation();
                    this.showTourPoint(index);
                });
                paginationList.append(listEntry);
            });
            paginationList.append(nextButton);
            paginationList.append(stopButton);

            paginationNav.append(paginationList);

            return paginationNav;
        }

        showTourPoint(index) {
            if (this.isActive) {
                this.hideTourPoints();
            }
            this.tourPoints[typeof index === "undefined" ? this.position : index].objects.dimBackground(
                {},
                () => {
                    $('.dimbackground-curtain').append(this.createPagination());
                },
                this.tourPoints[typeof index === "undefined" ? this.position : index].description
            );
            this.isActive = true;
        }

        hideTourPoints() {
            this.tourPoints.forEach((tourPoint) => {
                tourPoint.objects.undim();
            });
            this.isActive = false;

            return self;
        }

        startTour() {
            if (this.tourPoints.length > 0) {
                this.position = 0;
                this.showTourPoint();
            }
        }

        nextTourPoint() {

            if (this.tourPoints.length === 1) {
                console.log(`That's it! Tour finished`);
                this.endTour();
                return;
            }
            if (this.position + 1 === this.tourPoints.length) {
                this.position = 0;
                this.showTourPoint();
            } else {
                ++this.position;
                this.showTourPoint();
            }
        }

        previousTourPoint() {
            if (this.position === 0) {
                this.position = this.tourPoints.length - 1;
                this.showTourPoint()
            } else {
                this.position--;
                this.showTourPoint();
            }
        }

        endTour() {
            this.hideTourPoints();
        }

    }

    const interactiveTour = new InteractiveTour();

    // Dimming and popover as jQuery extension
    (function ($) {
        'use strict';

        let dimmedNodes = [];

        $.fn.dimBackground = function (options, callback, intel) {
            const params = parseParams(options, callback, intel);
            options = params.options;
            callback = params.callback;
            intel = params.intel;

            options = $.extend({}, $.fn.dimBackground.defaults, options);

            const $curtain = $(`<div class="dimbackground-curtain"></div>`);
            $curtain.css({
                position: 'fixed',
                left: 0,
                top: 0,
                width: '100%',
                height: '100%',
                background: 'black',
                opacity: 0,
                zIndex: options.curtainZIndex,
                display: 'flex',
                flexDirection: 'column-reverse'
            });
            $curtain.on('click', (event) => {
                if (event.target.classList.contains('dimbackground-curtain')) {
                    interactiveTour.endTour();
                }
            });
            $('body').append($curtain);

            this.each(function () {
                const $this = $(this);
                const opts = $.meta ? $.extend({}, options, $this.data()) : options;

                this._$curtain = $curtain;
                this._originalPosition = $this.css('position').toLowerCase();
                if (this._originalPosition !== "absolute" && this._originalPosition !== "fixed") {
                    $this.css('position', 'relative');
                }

                this._originalZIndex = $this.css('z-index');
                if (this._originalZIndex === "auto" || this._originalZIndex <= opts.curtainZIndex) {
                    $this.css('z-index', opts.curtainZIndex + 1);
                }

                $this.popover({
                    content: intel,
                    template: `<div class="popover" role="tooltip">
                                    <div class="arrow"></div>
                                    <h3 class="popover-header"></h3>
                                    <div class="popover-body"></div>
                                </div>`
                });

                $this.popover('show');
            });

            $curtain.stop().animate({opacity: options.darkness}, options.fadeInDuration, callback);
            dimmedNodes.push({$curtain: $curtain, $nodes: this});
            return this;
        };

        $.fn.undim = function (options, callback) {
            let params = parseParams(options, callback);
            options = params.options;
            callback = params.callback;
            options = $.extend({}, $.fn.dimBackground.defaults, options);

            let $curtain;
            const nodeZIndexMap = [];
            this.each(function () {
                let $this = $(this);
                let opts = $.meta ? $.extend({}, options, $this.data()) : options;

                if (this._$curtain) {
                    if (!$curtain) {
                        $curtain = this._$curtain;
                    }
                    if (typeof this._originalPosition != "undefined") {
                        nodeZIndexMap.push([this, this._originalPosition, this._originalZIndex]);
                    }
                    this._$curtain = undefined;
                    this._originalPosition = undefined;
                    this._originalZIndex = undefined;
                }

                $this.popover('hide');
            });

            if ($curtain) {
                $curtain.animate({opacity: 0}, options.fadeOutDuration, function () {
                    for (let i = 0; i < nodeZIndexMap.length; i++) {
                        const node = nodeZIndexMap[i][0],
                            position = nodeZIndexMap[i][1],
                            zIndex = nodeZIndexMap[i][2];
                        $(node).css({
                            position: position,
                            zIndex: zIndex
                        });
                    }
                    $curtain.remove();
                    callback();
                });

                let match;
                for (let i = 0; i < dimmedNodes.length; i++) {
                    const entry = dimmedNodes[i];
                    if (entry.$curtain == $curtain) {
                        match = i;
                        break;
                    }
                }
                if (match) {
                    dimmedNodes = dimmedNodes.slice(0, match).concat(dimmedNodes.slice(match + 1));
                }
            }
            return this;
        };

        $.undim = function (options, callback) {
            const params = parseParams(options, callback);
            options = params.options;
            callback = params.callback;
            options = $.extend({}, $.fn.dimBackground.defaults, options);

            const _dimmedNodes = dimmedNodes.slice();

            let completed = 0, total = _dimmedNodes.length;
            for (let i = 0; i < dimmedNodes.length; i++) {
                _dimmedNodes[i].$nodes.undim(options, done);
            }

            if (total === 0) {
                callback();
            }

            function done() {
                completed++;
                if (completed === total) {
                    callback();
                }
            }
        };

        $.fn.dimBackground.defaults = {
            darkness: 0.75,
            fadeInDuration: 250,
            fadeOutDuration: 250,
            curtainZIndex: 999
        };

        function parseParams(options, callback, intel) {
            if (typeof options == "function") {
                callback = options;
                intel = callback;
                options = {};
            }
            if (typeof options != "object") {
                options = {};
            }
            if (typeof callback != "function") {
                callback = function () {
                };
            }

            return {options, callback, intel};
        }
    }(jQuery));

    $(() => {

        /*if (!isLoggedIn()) {
            interactiveTour.startTour();
        }*/

        /* TODO Remove if check is implemented*/
        setTimeout(() => {
            interactiveTour.startTour();
        }, 1000);

    });
});
