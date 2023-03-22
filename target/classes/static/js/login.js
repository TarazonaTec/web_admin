$(document).ready(function() {
 let btnLogin=document.querySelector('#btnLogin');

 btnLogin.addEventListener('click',iniciarSesion)

});

async function iniciarSesion(){
 let datos={};
 
 datos.email=document.querySelector('#txtEmail').value;
 datos.password=document.querySelector('#txtPassword').value;


    const request = await fetch('api/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
     body: JSON.stringify(datos)
    });
    const response = await request.text();
    
    if(response!="nn"){
	 
	  localStorage.token=response;
	  
	   window.location.href="index.html";
     }
     else{
	  alert("credenciales incorrectas");
    }

  

}