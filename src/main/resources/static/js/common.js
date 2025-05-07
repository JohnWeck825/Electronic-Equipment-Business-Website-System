$(document).ready(function () {
    $('#btn-logout').on('click', function (e) {
        e.preventDefault();
        showModalLogout('Logout', 'Are you sure you want to logout?');
    })
})

function showModalLogout(title, message) {
    $('#modal-logout .modal-title').text(title);
    $('#modal-logout .modal-body').text(message);
    $('#modal-logout').modal('show');
}

function showModalOption(title, message) {
    console.log("Title: " , title);
    console.log("Message: " , message);
    $('#modal-yes-no .modal-title').text(title);
    $('#modal-yes-no .modal-body').text(message);
    // console.log($('#modal-yes-no'))
    $('#modal-yes-no').modal('show');
}

function showModalNotify(title, message) {
    $('#modal-notify .modal-title').text(title);
    $('#modal-notify .modal-body').text(message);
    $('#modal-notify').modal('show');
}