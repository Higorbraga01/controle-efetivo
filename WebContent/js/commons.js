$(document).ready(function(){
    $(window).resize(function() {
        //console.debug($("#bc1 a:hidden"))
        ellipses1 = $("#bc1 :nth-child(2)")
        if ($("#bc1 a:hidden").length >0) {ellipses1.show()} else {ellipses1.hide()}
        
        ellipses2 = $("#bc2 :nth-child(2)")
        if ($("#bc2 a:hidden").length >0) {ellipses2.show()} else {ellipses2.hide()}
    })
    
});

// RESPONSAVEL POR EFETUAR A CAPTURA DE TECLAS
window.addEventListener("keydown", function (event) {
	// RESPONSAVEL POR VERIFICAR SE A TECLA ESC FOI PRESSIONADA
	if (event.keyCode == 27 || event.wich == 27){
		ocultarExibirMenu(true);
	}	
}, true);

// FUNCAO RESPONSAVEL POR OCULTAR UMA DIV E EXIBIR OUTRA
function ocultarExibirDiv(divOcultar, divExibir){
	$( divOcultar ).hide( 300 );
	$( divExibir ).slideDown( 1000 );
}

// FUNCAO RESPONSAVEL POR ABRIR E FECHAR O MENU SUSPENSO
function ocultarExibirMenu(ocultar){
	if (ocultar){
		document.getElementById('sidebar-menu').style.display = 'none';
	} else {
		if (document.getElementById('sidebar-menu').style.display == 'none'){
			document.getElementById('sidebar-menu').style.display = 'block';
		} else {
			document.getElementById('sidebar-menu').style.display = 'none';
		}						
	}
}