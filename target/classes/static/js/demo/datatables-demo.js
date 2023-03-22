// Call the dataTables jQuery plugin
$(document).ready(function() {
  agregarUsuariosTables();
  $('#usuarios').DataTable();

});

async function cargarUsuarios(method){

    const request = await fetch('api/usuarios', {
      method: method,
      headers: getHeader()
      //body: JSON.stringify({a: 1, b: 'Textual content'})
    });
    const usuarios = await request.json();
    return usuarios;

}

async function agregarUsuariosTables(){
var listUsuarios=await cargarUsuarios('GET');

let usuario=``;
let telefono='';
listUsuarios.forEach(us=>{
	telefono=us.telefono==null?'-':telefono;
	console.log(telefono);
    usuario+=` <tr>
     <td>${us.id}</td>
     <td>${us.nombre} ${us.apellido}</td>
      <td>${us.email}</td>
        <td>${telefono}</td>
          <td><a href="#" class="btn btn-danger btn-circle btn-sm">
             <i class="fas fa-trash" onclick="eliminarUsuario(${us.id})"></i>
               </a></td>

                                                       </tr>
               `;
});
document.querySelector("#usuarios tbody").outerHTML=usuario;


}

function getHeader(){
	return{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization':localStorage.token
      }
}

async function eliminarUsuario(id){
	
	if(!confirm('desea eliminar este usuario')){
        return;
	}
	
	 const request = await fetch('api/usuarios/'+id, {
      method: 'DELETE',
      headers: getHeader()
      //body: JSON.stringify({a: 1, b: 'Textual content'})
    });
  	
    location.reload();
    
    
    
}



