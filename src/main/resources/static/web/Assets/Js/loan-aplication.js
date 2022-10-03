const { createApp } = Vue

createApp({
  data() {
    return {
      accounts: [],
      dataLoans: [],
      loanType: 0,
      amount: 0,
      payments: 0,
      paymentsLoans: [],
      percentageLoans: Number,
      maxAmount: 0,
      accountOwner: "",
      errotText: "",
      id: 0,
      paymentValue: 0,
      showPayment: false,
      objeto: {
        id: 0,
        amount: 0,
        payments: 0,
        accountOwner: "",
      }
    }
  },
  created() {
    this.showData();

  },
  methods: {

    newLoan() {
      this.objeto = {
        id: this.id,
        amount: this.amount,
        payments: this.payments,
        accountOwner: this.accountOwner,
      }
      let loan = this.dataLoans.filter(loan => loan.id == this.id)
      const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'buttonAccept',
          cancelButton: 'buttonCancel'
        },
        buttonsStyling: false
      })

      swalWithBootstrapButtons.fire({
        title: '¿Estás seguro?',
        text: "Estas por pedir un  " + loan[0].name + " por un total de " + this.numberFinal(this.amount) + ".",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
          axios.post("/api/loans", this.objeto)
            .then((response) => {
              swalWithBootstrapButtons.fire({
                title: 'Solicitud de ' + loan[0].name + '.',
                text: "El prestamo se acredito exitosamente",
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
    showData() {
      axios.get(`/api/clients/current`)
        .then((response) => {
          this.accounts = response.data.accounts
          console.log(this.accounts);
        })
    },

    showPayments(id) {
      this.id = id
      console.log(this.id);
      axios.get(`/api/loans`)
        .then((response) => {
          this.dataLoans = response.data
          let loan = this.dataLoans.filter(loan => loan.id == this.id)
          this.maxAmount = loan[0].maxAmount
          this.paymentsLoans = loan[0].paymentLoan
          this.percentageLoans = loan[0].percentage
          console.log(this.percentageLoans);

        })
    },

    showpaymentvalue() {
      if (this.showPayment == false) {
        this.showPayment = true;
      } else {
        this.showPayment = false;
      }
      console.log(this.showPayment);
      this.paymentValue = (this.amount / this.payments) * this.percentageLoans

    },
    numberFinal(number) {
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', minimumFractionDigits: 2 }).format(number);

    },
    dosDecimales(n) {
      let t = n.toString();
      let regex = /(\d*.\d{0,2})/;
      return t.match(regex)[0];
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
  showPaymentsMortgage = body.querySelector("#defaultCheck1"),
  showPaymentsPersonal = body.querySelector("#defaultCheck2"),
  showPaymentsCar = body.querySelector("#defaultCheck3"),
  paymentsMortgage = body.querySelector(".paymentsMortgage"),
  paymentsPersonal = body.querySelector(".paymentsPersonal"),
  paymentsCar = body.querySelector(".paymentsCar"),
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
showPaymentsMortgage.addEventListener("click", validaCheckbox1, false);
function validaCheckbox1() {

  if (showPaymentsMortgage.checked) {
    paymentsMortgage.classList.toggle("show1");
    paymentsPersonal.classList.remove("show1");
    paymentsCar.classList.remove("show1");
  }
}
showPaymentsPersonal.addEventListener("click", validaCheckbox2, false);
function validaCheckbox2() {

  if (showPaymentsPersonal.checked) {
    paymentsPersonal.classList.toggle("show1");
    paymentsMortgage.classList.remove("show1");
    paymentsCar.classList.remove("show1");
  }
}
showPaymentsCar.addEventListener("click", validaCheckbox3, false);
function validaCheckbox3() {

  if (showPaymentsCar.checked) {
    paymentsCar.classList.toggle("show1");
    paymentsPersonal.classList.remove("show1");
    paymentsMortgage.classList.remove("show1");

  }
}
/*HAMBURGER*/
const menu = document.querySelector('.hamburger');
function toggleMenu(event) {
  this.classList.toggle('is-active');
  document.querySelector(".menuppal").classList.toggle("is_active");
  event.preventDefault();
}
menu.addEventListener('click', toggleMenu, false);



