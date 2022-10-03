const { createApp } = Vue

createApp({
    data() {
        return {
            dataApi: [],
            accountTransactions: [],
            nameAccount: "",
            textError: "",
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('id');
        this.showAccountData(myParam)
    },

    methods: {
        showAccountData(id) {
            axios.get(`/api/accounts/${id}`)
                .then((response) => {
                    this.dataApi = response.data
                    this.accountTransactions = this.dataApi.transaction
                    this.nameAccount = this.dataApi.number
                    this.accountTransactions.sort((a, b) => {  // [...this.clientAccounts].sort((a, b) => a.id - b.id)
                        if (a.id > b.id) {
                            return -1;
                        } else if (a.id < b.id) {
                            return 1;
                        } else {
                            return 0;
                        }
                    })
                    console.log(this.accountTransactions)
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






