 
$(function() {

    function login() {
        $("#btn-login").click(function (e) {
            Swal.fire({
            title: 'Login',
                html:
                '<input id="swal-input1" class="swal2-input" placeholder="Username">' +
                '<input id="swal-input2" class="swal2-input" placeholder="Password" type="password">',
            focusConfirm: false,
            preConfirm: () => {
                return [
                    document.getElementById('swal-input1').value,
                    document.getElementById('swal-input2').value
                ]
            }
            }).then((result) => {
                if (result.isConfirmed) {
                const username = result.value[0];
                const password = result.value[1];
                let LoginFormResult = {
                    username: username,
                    password: password
                }

                $.ajax({
                    type: "post",
                    url: contextPath + '/login.do',
                    data: LoginFormResult,
                    dataType: "json",
                    success: function (response) {
                        //Swal.fire('Credentials', `Username: ${username}<br>Password: ${password}`, 'success');
                        
                        window.location.href=contextPath+'/redirect?method=home';

                        
                        validarSession();
                    },
                    error: function(xhr, status, error) {
                                 
                    }
                });  

                    }
                });
            
        });

    }
   
    function validarSession() {
        $.ajax({
            type: "get",
            url: contextPath+"/existeSession.do",
            data: "data",
            dataType: "json",
            success: function (response) {
                alert ("se logeo");
            },
            error: function(xhr, status, error) {
                              
                }
            
        });
    }

    $(document).ready(function () {
        login();
        validarSession();
    });

});