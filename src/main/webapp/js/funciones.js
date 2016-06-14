/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

   
  
        function b() {alert(document.getElementById("form:j_idt64_input").checked)
            if (document.getElementById("form:j_idt64_input").checked) {
                document.getElementById("form:j_idt68_input").checked=true
                document.getElementById("form:j_idt72_input").checked=true
                
            }
        }


function validateDates() {  
    setTimeout(function(){var date1 = document.getElementById("form:fhInicial_input").value.split("-");
    var date2 = document.getElementById("form:fhFin_input").value.split("-");
    f1 = new Date(date1[2], date1[1] - 1, date1[0]);
    f2 = new Date(date2[2], date2[1] - 1, date2[0]);
    if(f2<=f1){
        alert("Fecha Invalida .l.");
      
        document.getElementById("form:fhFin_input").value="";
    }},1000)
}
