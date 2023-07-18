function dropdown() {
    document.querySelector('#pomenu').classList.toggle('hidden');
    document.querySelector('#arrowdown').classList.toggle('rotate-0');

}



function OpenNav() {
    document.querySelector('#sidenav').classList.toggle('w-[6%]');
    document.querySelector('#navcontent').classList.toggle('hidden');
    document.querySelector('#logouttext').classList.toggle('hidden');


}

const togglePasswordButton = document.getElementById('togglePassword');
  const passwordField = document.getElementById('password');
  togglePasswordButton.addEventListener('click', () => {
      passwordField.type = passwordField.type === 'password' ? 'text' : 'password';
});

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

var editor = new Quill('#rich-text', {
  theme: 'snow',
  modules: {
    toolbar: [
      ['bold', 'italic', 'underline', 'strike'], // Text formatting options
      ['blockquote', 'code-block'], // Additional formatting options
      [{ 'list': 'ordered' }, { 'list': 'bullet' }], // Bullet and numbered lists
      ['clean'] // Remove formatting option
    ]
  }
});

function togglePasswordVisibility() {
  const passwordInput = document.getElementById("password");
  const icon = document.querySelector("#password + span svg");

  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    icon.classList.remove("text-gray-700");
    icon.classList.add("text-blue-500");
  } else {
    passwordInput.type = "password";
    icon.classList.remove("text-blue-500");
    icon.classList.add("text-gray-700");
  }
}