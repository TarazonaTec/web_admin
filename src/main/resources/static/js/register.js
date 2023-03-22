// Call the dataTables jQuery plugin
$(document).ready(function() {
 let btnRegister=document.querySelector('#btnRegister');

 btnRegister.addEventListener('click',registrarUsuario)

});



async function registrarUsuario(){
 let datos={};
 
 datos.nombre=document.querySelector('#txtName').value;
 datos.apellido=document.querySelector('#txtLastName').value;
 datos.email=document.querySelector('#txtEmail').value;
 datos.password=document.querySelector('#txtPassword').value;

  let repeatPassword= document.querySelector('#txtRepeatPassword').value;
  
  if(repeatPassword!=datos.password){
	 alert("la contraseña que escribiste es diferente");
	 return;
}

    const request = await fetch('api/usuarios', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
     body: JSON.stringify(datos)
    });
   
   alert("cuenta creada con exito");
   window.location.href="login.html";
   
  
  

}