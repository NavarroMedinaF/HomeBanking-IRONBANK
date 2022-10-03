const { createApp } = Vue

createApp({
    data() {
        return {

            nameLoan: "",
            maxAmount: 0,
            percentage: 0,
            payments: [],
            newLoan: {
                name: "",
                maxAmount: 0,
                percentage: Number,
                payments: [],
            },
            dataApi: [],
            clientes: [],
            cliente: {
                name: "",
                lastName: "",
                email: "",


            },
            inputName: "",
            inputLastName: "",
            inputEmail: "",
            inputNameModi: "",
            inputLastNameModi: "",
            inputEmailModi: "",
            inputNameMuestra: "",
            inputLastMuestra: "",
            inputEmailMuestra: "",
            idMuestra: "",
            clienteNuevo: {
                name: "",
                lastName: "",
                email: "",
            }
        }
    },
    created() {
        this.LoadData()


    },

    methods: {

        creatNewLoan() {
            this.newLoan = {
                name: this.nameLoan,
                maxAmount: this.maxAmount,
                percentage: this.percentage,
                payments: this.payments,
            }
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por crear nuevo tipo de prestamo " + this.nameLoan + " en IRONBAKN",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/admin/loans", this.newLoan)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Creacion de Prestamo',
                                text: "El prestamo se ah creado exitosamente",
                                icon: 'success',
                                showCancelButton: true,
                                confirmButtonText: 'Aceptar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                            })
                            setTimeout(() => {
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

        LoadData() {
            axios.get(`/api/clients`)
                .then((response) => {
                    this.dataApi = response.data
                    this.clientes = this.dataApi
                })
        },


        //         this.cliente = {
        //           name: this.inputName,
        //           lastName: this.inputLastName,
        //           email: this.inputEmail,

        //         };
        //         axios.post(`/rest/clients`, this.cliente).then((result) => {   
        //         console.log(result);
        //         })
        //         .then(() => this.LoadData());//para llamar a la funcion y no tener q recargar la pagina para mostrar


        // },
        axiosLoadCLientfunction() {
            this.cliente = {
                name: this.inputName,
                lastName: this.inputLastName,
                email: this.inputEmail,
            };
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por crear un Cliente en IRONBAKN",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post(`/rest/clients`, this.cliente)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Creacion de cliente',
                                text: "El cliente se ah creado exitosamente",
                                icon: 'success',
                                showCancelButton: true,
                                confirmButtonText: 'Aceptar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                            })
                            setTimeout(() => {
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


        //     axios.delete(`/rest/clients/${id}`)
        //     .then((response)=> console.log(response))
        //     .then(() => this.LoadData())

        //     .catch(() => console.log('no funciona'))

        //  },

        axiosDeleteFunction(id) {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por eliminar un cliente de IRONBAKN",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(`/rest/clients/${id}`)
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Eliminar cliente',
                                text: "El cliente ha sido eliminado exitosamente",
                                icon: 'success',
                                showCancelButton: true,
                                confirmButtonText: 'Aceptar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                            })
                            setTimeout(() => {
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


        //     this.clienteNuevo={
        //         name:this.inputNameModi,
        //         lastName:this.inputLastNameModi,
        //         email:this.inputEmailModi
        //         }


        //     axios.patch(`/rest/clients/${id}`,this.clienteNuevo)//trabajar sin function, usar funcion vacia!!
        //     .then((response)=> console.log(response))
        //     .then(() => this.LoadData())
        //     .catch((error)=>console.log(error));



        //  },
        axiosModifyUser(id) {
            this.clienteNuevo = {
                name: this.inputNameModi,
                lastName: this.inputLastNameModi,
                email: this.inputEmailModi
            }

            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por modificar un cliente de IRONBAKN",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch(`/rest/clients/${id}`, this.clienteNuevo)//trabajar sin function, usar funcion vacia!!
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Modificar Cliente',
                                text: "El cliente se ha modificado exitosamente.",
                                icon: 'success',
                                showCancelButton: true,
                                confirmButtonText: 'Aceptar',
                                cancelButtonText: 'Cancelar',
                                reverseButtons: true
                            })
                            setTimeout(() => {
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

        modalClientes(cliente) {
            this.inputNameMuestra = cliente.name
            this.inputLastMuestra = cliente.lastName
            this.inputEmailMuestra = cliente.email
            this.idMuestra = cliente.id
            modal.classList.add('modal--show');
        },
        ocultar() {
            modal.classList.remove('modal--show');
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





    },



    logout() {


    },






    computed: {

    }



}).mount('#app')

const body = document.querySelector('body'),
    sidebar = body.querySelector('nav'),
    toggle = body.querySelector(".toggle"),
    modeSwitch = body.querySelector(".toggle-switch"),
    modelSwicthHamburger = body.querySelector(".toggle-switchH"),
    modeText = body.querySelector(".mode-text"),
    modeText1 = body.querySelector(".mode-text1"),
    openModal = document.getElementsByClassName('herocta'),
    modal = document.querySelector('.modal'),
    closeModal = document.querySelector('.modalclose'),
    mainText = document.getElementsByClassName("main"),
    cardBColor = document.getElementsByClassName("card-single");
console.log(openModal);






toggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
})

modeSwitch.addEventListener("click", () => {
    body.classList.toggle("dark");
    mainText.classList.toggle("dark")
    cardBColor.classList.toggle("dark")
    if (body.classList.contains("dark")) {
        modeText.innerText = "Light mode";
    } else {
        modeText.innerText = "Dark mode";
    }
});

modelSwicthHamburger.addEventListener("click", () => {
    body.classList.toggle("dark");
    // mainText.classList.toggle("dark") 
    // cardBColor.classList.toggle("dark")
    if (body.classList.contains("dark")) {
        console.log("hola");
        modeText1.innerText = "Light mode";
    } else {
        modeText1.innerText = "Dark mode";
        console.log("hola1");
    }
});
/*HAMBURGER*/
const menu = document.querySelector('.hamburger');
function toggleMenu(event) {
    this.classList.toggle('is-active');
    document.querySelector(".menuppal").classList.toggle("is_active");
    event.preventDefault();
}
menu.addEventListener('click', toggleMenu, false);






