const { createApp } = Vue

createApp({
    data() {
        return {
            cards: [],
            today: new Date(),
            now: "",
            showDefeatedCard: Boolean,
            todayNumber: Number,

        }
    },
    created() {
        this.showCard()
        this.dateToday()
        this.cardDefeated()


    },

    methods: {
        showCard() {
            axios.get("/api/card")
                .then((response) => {
                    this.cards = response.data
                    console.log(this.cards)
                    console.log(this.cards[0].thruDate);
                    console.log(this.now);
                    this.fechatarjeta(this.cards[0].thruDate)
                })
                ;
        },


        deleteCard(numberCard) {
            console.log(numberCard);
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'buttonAccept',
                    cancelButton: 'buttonCancel'
                },
                buttonsStyling: false
            })

            swalWithBootstrapButtons.fire({
                title: '¿Estás seguro?',
                text: "Estas por eliminar esta tarjeta " + numberCard + ".",
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: 'Aceptar',
                cancelButtonText: 'Cancelar',
                reverseButtons: true
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.patch(`/api/clients/current/card`, "number=" + numberCard)

                        .then((response) => {
                            swalWithBootstrapButtons.fire({
                                title: 'Eliminar tarjeta de credito',
                                text: "La tarjeta se ah eliminado exitosamente",
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
        dateToday() {
            this.now = this.today.toISOString().substr(0, 10)
        },


        cardDefeated() {
            let year = this.now.slice(0, -6)
            let mouth = this.now.slice(5, -3)
            let day = this.now.slice(8)

            this.todayNumber = new Number(year + mouth + day)
            //console.log(typeof this.todayNumber<1); 
        },

        dateCard(date) {
            let year = date.slice(0, -6)
            let mouth = date.slice(5, -3)
            let day = date.slice(8)
            let defeated = new Number(year + mouth + day)

            if (this.todayNumber > defeated) {
                return true
            } else {
                return false
            }

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



