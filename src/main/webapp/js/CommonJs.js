function showAlert(message,alertType){
    let alert =  ' <div className="alert '+alertType+' alert-dismissible fade show" role="alert"> ' +
        '<strong>' +message+'</strong> ' +
        '<button type="button" className="close" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> ' +
        '</div>';

    console.log("alter : ",alert);

    return alert;
}

function displayToast(heading, message, icon, bgColour) {
    $.toast({
        heading: heading,
        text: message,
        icon: icon,
        loaderBg: 'white',  // To change the background
        hideAfter: 3200,   // in milli seconds
        position: 'top-center',
        showHideTransition: 'slide',
        allowToastClose: true,
        bgColour: bgColour,
        // bgColor: getComputedStyle(document.body).getPropertyValue('--main-bg-color').trim(),
        textColor: 'text-white',
    });
}