const questionMarkIcon = document.querySelector('.question-mark-icon');
const explanationModal = document.getElementById('explanationModal');
const closeModalButton = document.getElementById('closeModal');
const companySelect = document.getElementById('companyCode');
const pdfDownloadDiv = document.getElementById('pdfDownloadpc');
const source = document.getElementById('sourcingJustification');
const pdfDownloadsj = document.getElementById('pdfDownloadsj');
const pdfDownloadPR = document.getElementById('pdfDownloadPR');
const uploadSrc = document.getElementById('uploadSrc');
const uploadPR = document.getElementById('uploadPR');

companySelect.addEventListener('change', function() {
  // Get the selected option value
  const selectedValue = companySelect.value;

  // Check if the selected value is "FortisBC Inc. (3700)"
  if (selectedValue === "FortisBC Inc. (3700)") {
    // If selected, show the PDF download div
    pdfDownloadPR.style.display = "block";
    uploadPR.style.display = "block";
  } else {
    // If not selected, hide the PDF download div
    pdfDownloadPR.style.display = "none";
    uploadPR.style.display = "none";
  }
});
source.addEventListener('change', function() {
  // Get the selected option value
  const selectedValue = source.value;

  // Check if the selected value is "FortisBC Inc. (3700)"
  if (selectedValue === "Yes") {
    // If selected, show the PDF download div
    pdfDownloadsj.style.display = "block";
    uploadSrc.style.display = "block";
  } else {
    // If not selected, hide the PDF download div
    uploadSrc.style.display = "none";
    pdfDownloadsj.style.display = "none";
  }
});

companySelect.dispatchEvent(new Event('change'));
source.dispatchEvent(new Event('change'));

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


