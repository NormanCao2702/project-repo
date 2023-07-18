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
