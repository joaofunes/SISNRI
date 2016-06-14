function handleSubmit(xhr, status, args, dialog) {
    var jqDialog = jQuery('#' + dialog.id);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
        return false;
    } else {
        dialog.hide();
    }
}

function myrefresh()
{
    if (document.getElementsByClassName("ui-messages-error-summary").length !== 0) {
        alert("have an error in CSS!");
        window.location.reload();
    }
} 