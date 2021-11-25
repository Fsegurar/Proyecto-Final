
(function() {
    const forms = document.getElementsByClassName('needs-validation');
    const validation = Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                console.log(form.checkValidity())
                event.preventDefault();
                event.stopPropagation();
            } else if (form.checkValidity() === true) {
                let username = document.getElementById("username-input-crear").value
                let psw1 = document.getElementById("pw-input-crear").value
                let psw2 = document.getElementById("pw2-input-crear").value
                let user = new UserAppService();
                for (var i = 0; i < user.listUsers().size(); i++) {
                    if (user.listUsers().get(i).getUsername().equalsIgnoreCase(username)) {
                        document.getElementById("username-error").setAttribute("value", "nombre de usuario ya existe")
                        event.preventDefault();
                        event.stopPropagation();
                    }
                }
                if (!psw1.equals(psw2)) {
                    document.getElementById("psw1-error").setAttribute("value", "Las contraseñas no coinciden")
                    document.getElementById("psw2-error").setAttribute("value", "Las contraseñas no coinciden")
                    event.preventDefault();
                    event.stopPropagation();
                }

            }
            ;
            form.classList.add('was-validated');
        }, false);
    });

})();
