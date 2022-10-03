const { createApp } = Vue

createApp({
    data() {
      return {
        typeTransaction:"",
        numberMyAccount:"",
        numberAccount:"",
        amount:"",
        description:"",
        errorText:"",
        accounts:[],
        accountFilters:[],
        accountBalanceShow:"",
        
    }
},
created() {
this.showData();
 
},
methods: {

  newTranfer(){
    const swalWithBootstrapButtons = Swal.mixin({
                    customClass: {
                        confirmButton: 'buttonAccept',
                        cancelButton: 'buttonCancel'
                    },
                    buttonsStyling: false
                })
            
                swalWithBootstrapButtons.fire({
                    title: '¿Estás seguro?',
                    text: "Estas por una tranferencia de " + this.numberMyAccount +" a "+ this.numberAccount + " de " +  this.amount + ".",
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonText: 'Aceptar',
                    cancelButtonText: 'Cancelar',
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
        axios.post("/api/clients/current/transactions","accountOrigin=" + this.numberMyAccount + "&accountDestiny=" + this.numberAccount + "&description=" +
    this.description + "&amount=" + this.amount,
    { headers: { 'content-type': 'application/x-www-form-urlencoded'}})
    .then((response)=>{
            swalWithBootstrapButtons.fire({
                title: 'Nueva Transferencia',
                text: "La transferencia se ah realizado exitosamente",
                icon: 'success',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            })
            setTimeout(()=> {
                location.reload();
              }, 2000);
        })
        .catch(response => {
                            const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                    cancelButton: 'buttonCancel'
                                },
                                buttonsStyling: false
                            })
            
                            swalWithBootstrapButtons.fire({
                                title: "Hemos detectado un error",
                                text: response.response.data,
                                icon: "error",
                                showConfirmButton: false,
                                showCancelButton: true,
                                cancelButtonText: 'Aceptar',
                            })
                        })
                                }
        })
      },
    
   showData(){
    axios.get(`/api/accounts`)
    .then((response)=>{
        this.accounts=response.data
        console.log(this.accounts);
    })
   },

   showAccountfilter(){
    this.accountFilters= this.accounts.filter(account=> !account.number.includes(this.numberMyAccount))
   },

   logout() {
    const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })
        
            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por salir de tu Homebanking",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    
   
        axios.post('/api/logout').then(response => {
            window.location.href = 'http://localhost:8080'
        })
        .catch(response => {
                            const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                    cancelButton: 'buttonCancel'
                                },
                                buttonsStyling: false
                            })
            
                            swalWithBootstrapButtons.fire({
                                title: "Hemos detectado un error",
                                text: response.response.data,
                                icon: "error",
                                showConfirmButton: false,
                                showCancelButton: true,
                                cancelButtonText: 'Aceptar',
                            })
                        })
                    }
                })
            },
  numberFinal(number){
    return new Intl.NumberFormat('en-US', {style: 'currency',currency: 'USD', minimumFractionDigits: 2}).format(number);
    
},
},

computed: {
   
}

}).mount('#app')





const body = document.querySelector('body'),
sidebar = body.querySelector('nav'),
toggle = body.querySelector(".toggle"),
modeSwitch = body.querySelector(".toggle-switch"),
modelSwicthHamburger=body.querySelector(".toggle-switchH"),
modeText = body.querySelector(".mode-text"),
modeText1 = body.querySelector(".mode-text1"),
mainText= document.getElementsByClassName("main"),
showMyaccounts=body.querySelector("#defaultCheck1"),
showOtheraccounts=body.querySelector("#defaultCheck2"),
myAccounts=body.querySelector(".myAccounts"),
otherAccounts=body.querySelector(".otherAccounts"),
cardBColor=document.getElementsByClassName("card-single");

toggle.addEventListener("click" , () =>{
sidebar.classList.toggle("close");
})

modeSwitch.addEventListener("click" , () =>{
body.classList.toggle("dark");
mainText.classList.toggle("dark") 
cardBColor.classList.toggle("dark")
if(body.classList.contains("dark")){
modeText.innerText = "Light mode";
}else{
modeText.innerText = "Dark mode";
}
});

modelSwicthHamburger.addEventListener("click" , () =>{
    body.classList.toggle("dark");
    // mainText.classList.toggle("dark") 
    // cardBColor.classList.toggle("dark")
    if(body.classList.contains("dark")){
        console.log("hola");
    modeText1.innerText = "Light mode";
    }else{
    modeText1.innerText = "Dark mode";
    console.log("hola1");
    }
    });
/*HAMBURGER*/ 
const menu = document.querySelector('.hamburger');
function toggleMenu (event) {
  this.classList.toggle('is-active');
  document.querySelector( ".menuppal" ).classList.toggle("is_active");
  event.preventDefault();
}
menu.addEventListener('click', toggleMenu, false);

/*ACCOUNTS*/
showMyaccounts.addEventListener("click", validaCheckbox1, false);
function validaCheckbox1(){
  
  if(showMyaccounts.checked){
    myAccounts.classList.toggle("show");  
    otherAccounts.classList.remove("show");
    
  }
}

showOtheraccounts.addEventListener("click", validaCheckbox2, false);
function validaCheckbox2(){
  
    if(showOtheraccounts.checked){
        otherAccounts.classList.toggle("show");  
        myAccounts.classList.remove("show");
       
      }
    }  

 
    // createAccountSaving(){
//   const swalWithBootstrapButtons = Swal.mixin({
//               customClass: {
//                   confirmButton: 'buttonAccept',
//                   cancelButton: 'buttonCancel'
//               },
//               buttonsStyling: false
//           })
      
//           swalWithBootstrapButtons.fire({
//               title: '¿Estás seguro?',
//               text: "Estas por crear una Cuenta de Ahorro en IRONBAKN",
//               icon: 'question',
//               showCancelButton: true,
//               confirmButtonText: 'Aceptar',
//               cancelButtonText: 'Cancelar',
//               reverseButtons: true
//           }).then((result) => {
//               if (result.isConfirmed) {
//   axios.post("/api/clients/current/accounts", "type=SAVING")
//   .then((response)=>{
//       swalWithBootstrapButtons.fire({
//           title: 'Creacion de Cuenta de Ahorro',
//           text: "La cuenta se ah creado exitosamente",
//           icon: 'success',
//           showCancelButton: true,
//           confirmButtonText: 'Aceptar',
//           cancelButtonText: 'Cancelar',
//           reverseButtons: true
//       })
//       setTimeout(()=> {
//           location.reload();
//         }, 2000);
//   })
//   .catch(response => {
//                       const swalWithBootstrapButtons = Swal.mixin({
//                           customClass: {
//                               cancelButton: 'buttonCancel'
//                           },
//                           buttonsStyling: false
//                       })
      
//                       swalWithBootstrapButtons.fire({
//                           title: "Hemos detectado un error",
//                           text: response.response.data,
//                           icon: "error",
//                           showConfirmButton: false,
//                           showCancelButton: true,
//                           cancelButtonText: 'Aceptar',
//                       })
//                   })
//                           }
//   })
// },