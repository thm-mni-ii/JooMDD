let firstVisit = true;


function startTour() {
    console.log('This is your first visit.');

}

function dimBackground(options) {
    // Prepare curtain
    let curtain = $('<div class="background-curtain"></div>');
    curtain.css({
        position: 'fixed',
        left: 0,
        top: 0,
        width: '100%',
        height: '100%',
        background: black,
        opacity: 0,
        zIndex: 0
    });
    $('body').append(curtain);

    // Find highest z-index in page
    const zIndex = Math.max.apply(null, $.map($('body > *')), (e, n) => {
        return $(e).css('position') === 'absolute' ? parseInt($(e).css('z-index')) : 1;
    });

}

$(() => {

    if (firstVisit) {
        // TODO: Check if this is the first visit of that specific user to not annoy him with this tour
        startTour();
    }

});
