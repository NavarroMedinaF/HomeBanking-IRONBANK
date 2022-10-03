const { createApp } = Vue

createApp({
    data() {
        return {
            dataApi: [],
            clientAccounts: [],
            clientLoans: [],
            cards: [],
            errorText: "",
        }
    },
    created() {
        this.LoadData()
        this.showCard()
        this.loadAccount()
    },

    methods: {
        loadAccount() {
            axios.get(`/api/accounts`)
                .then((response) => {
                    this.clientAccounts = response.data
                    console.log(this.clientAccounts);
                })
                .catch(response => {
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
        },
        LoadData() {
            axios.get(`/api/clients/current`)
                .then((response) => {
                    this.dataApi = response.data
                    console.log(this.dataApi)

                    this.clientLoans = this.dataApi.loans
                    console.log(this.clientLoans);
                })
                .catch(response => {
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

        },

        createAccountSaving() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por crear una Cuenta de Ahorro en IRONBAKN",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/clients/current/accounts", "type=SAVING")
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Creacion de Cuenta de Ahorro',
                                text: "La cuenta se ah creado exitosamente",
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

        createAccountCurrent() {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por crear una Cuenta Corriente en IRONBAKN",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.post("/api/clients/current/accounts", "type=CURRENT")
                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Creacion de Cuenta Corriente',
                                text: "La cuenta se ah creado exitosamente",
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


        showCard() {
            axios.get(`/api/card`)
                .then((response) => {
                    this.cards = response.data
                    console.log(this.cards);
                })
        },

        deleteAccount(id) {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por eliminar esta cuenta ",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch(`/api/clients/current/account`, "id=" + id)
                        .then(response => {
                            setTimeout(() => {
                                location.reload();
                            }, 1000);
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

        numberFinal(number) {
            return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', minimumFractionDigits: 2 }).format(number);

        },
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
    mainText = document.getElementsByClassName("main"),
    cardBColor = document.getElementsByClassName("card-single");

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



