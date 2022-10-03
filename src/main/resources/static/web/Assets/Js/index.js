const { createApp } = Vue

createApp({
  data() {
    return {
      dataApi: [],
      email: "",
      password: "",
      nameNewClient: "",
      lastNameNewClient: "",
      emailNewClient: "",
      passwordNewClient: "",



    }
  },
  created() {

  },


  methods: {

    login() {
      axios.post('/api/login', "email=" + this.email + "&password=" + this.password,
        { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
        .then(response => {
          if (this.email == "admin@admin") {
            window.location.href = '/manager.html'
          } else {
            window.location.href = '/web/accounts.html'
          }

        }).catch(response => {
          const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
              cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
          })

          swalWithBootstrapButtons.fire({
            title: "Hemos detectado un error",
            text: "Email y/o contraseña incorrecto",
            icon: "error",
            showConfirmButton: false,
            showCancelButton: true,
            cancelButtonText: 'Aceptar',
          })
        })
    },

    newClient() {
      const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success',
          cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
      })

      swalWithBootstrapButtons.fire({
        title: '¿Estás seguro?',
        text: "Estas por crear una cuenta  en IRONBANK",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          axios.post('/api/clients', "name=" + this.nameNewClient + "&lastName=" + this.lastNameNewClient + "&email=" +
            this.emailNewClient + "&password=" + this.passwordNewClient,
            { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response => console.log('registered'))
            .then(response => {
              this.email = this.emailNewClient
              this.password = this.passwordNewClient
              this.login()
            }).catch(response => {
              const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                  cancelButton: 'btn btn-danger'
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

  },

  computed: {

  }

}).mount('#app')

const loginText = document.querySelector(".title-text .login");
const loginForm = document.querySelector("form.login");
const loginBtn = document.querySelector("label.login");
const signupBtn = document.querySelector("label.signup");
const signupLink = document.querySelector("form .signup-link a");

signupBtn.onclick = (() => {
  loginForm.style.marginLeft = "-50%";
  loginText.style.marginLeft = "-50%";
});
loginBtn.onclick = (() => {
  loginForm.style.marginLeft = "0%";
  loginText.style.marginLeft = "0%";
});
signupLink.onclick = (() => {
  signupBtn.click();
  return false;
});






//   const swalWithBootstrapButtons = Swal.mixin({
//       customClass: {
//           confirmButton: 'btn btn-success',
//           cancelButton: 'btn btn-danger'
//       },
//       buttonsStyling: false
//   })

//   swalWithBootstrapButtons.fire({
//       title: '¿Estás seguro?',
//       text: "Estas por crear una cuenta  en IRONBANK",
//       icon: 'question',
//       showCancelButton: true,
//       confirmButtonText: 'Aceptar',
//       cancelButtonText: 'Cancelar',
//       reverseButtons: true
//   }).then((result) => {
//       if (result.isConfirmed) {
//         axios.post('/api/clients', "name=" + this.nameNewClient + "&lastName=" + this.lastNameNewClient + "&email=" +
//         this.emailNewClient + "&password=" + this.passwordNewClient,
//         { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
//         .then(response => console.log('registered'))
//         .then(response => {
//           this.email = this.emailNewClient
//           this.password = this.passwordNewClient
//           this.login()
//         }).catch(response => {
//               const swalWithBootstrapButtons = Swal.mixin({
//                   customClass: {
//                       cancelButton: 'btn btn-danger'
//                   },
//                   buttonsStyling: false
//               })

//               swalWithBootstrapButtons.fire({
//                   title: "Hemos detectado un error",
//                   text: response.response.data,
//                   icon: "error",
//                   showConfirmButton: false,
//                   showCancelButton: true,
//                   cancelButtonText: 'Aceptar',
//               })
//           })
//       }
//   })
// }, 
