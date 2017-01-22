var hidePrint = function() {
    jQuery('#PrintDlgFrame').hide();
};
var updateresize = function() {
    jQuery('#PrintDlgFrame').css('height', jQuery('#PrintDlg').css('height'));
};
var print = function(xhr, status, args) {
//    console.log('ctx:' + ctx);
    if(typeof args.reportName!=='undefined'){
    jQuery('#PrintDlgFrame').attr('src', ctx + '/report?reportName=' + args.reportName);
    jQuery('#PrintDlgFrame').show();
   // PrintDialog.show();
    PF('PrintDialog').show();
    return false;    
    }    
};


var prints = function(xhr, status, args) {
//    console.log('ctx:' + ctx);
    if(typeof args.reportName!=='undefined'){
    jQuery('#PrintDlgFrame').attr('src', ctx + '/report?reportName=' + args.reportName);
    return false;    
    }    
};
        