const questionMarkIcon = document.querySelector('.question-mark-icon');
const explanationModal = document.getElementById('explanationModal');
const closeModalButton = document.getElementById('closeModal');

  questionMarkIcon.addEventListener('click', () => {
    explanationModal.classList.remove('hidden');
  });

  closeModalButton.addEventListener('click', () => {
    explanationModal.classList.add('hidden');
  });

const paymentTermSelect = document.getElementById('paymentTerm');
const otherPaymentTermContainer = document.getElementById('otherPaymentTermContainer');

paymentTermSelect.addEventListener('change', function() {
  if (this.value === 'other') {
    otherPaymentTermContainer.classList.remove('hidden');
  } else {
    otherPaymentTermContainer.classList.add('hidden');
  }
});


function dropdown() {
  document.querySelector('#pomenu').classList.toggle('hidden');
  document.querySelector('#arrowdown').classList.toggle('rotate-0');

}



function OpenNav() {
  document.querySelector('#navcontent').classList.toggle('hidden');
  document.querySelector('#logouttext').classList.toggle('hidden');
  document.querySelector('#sidenav').classList.toggle('w-16');
  document.querySelector('#logoutbutton').classList.toggle('w-fit');
  document.querySelector('#logoutbutton').classList.toggle('fixed');
  document.querySelector('#logoutbutton').classList.toggle('bottom-0');
}
