const togglePasswordButton = document.getElementById('togglePassword');
  const passwordField = document.getElementById('password');
  togglePasswordButton.addEventListener('click', () => {
      passwordField.type = passwordField.type === 'password' ? 'text' : 'password';
});

// const toggleconpas = document.getElementById('toggleconPassword');
//   const conpasswordField = document.getElementById('confirmPassword');
//   togglePasswordButton.addEventListener('click', () => {
//     passwordField.type = passwordField.type === 'password' ? 'text' : 'password';
// });


