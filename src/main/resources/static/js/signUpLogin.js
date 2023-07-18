const togglePasswordButton = document.getElementById('togglePassword');
  const passwordField = document.getElementById('password');
  togglePasswordButton.addEventListener('click', () => {
      passwordField.type = passwordField.type === 'password' ? 'text' : 'password';
});