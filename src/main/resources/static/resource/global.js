toastr.options = {
    closeButton: true,
    debug: false,
    newestOnTop: true,
    progressBar: true,
    positionClass: "toast-top-right",
    preventDuplicates: false,
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "5000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};

function toastWarning(msg) {
    toastr["warning"](msg, "경고");
}

function toastNotice(msg) {
    toastr["success"](msg, "성공");
}

function getQueryParams() {
    const params = new URLSearchParams(window.location.search);
    const paramsObj = {};

    for (const [key, value] of params) {
        paramsObj[key] = value;
    }

    return paramsObj;
}

$(function () {
    $('select[value]').each(function (index, el) {
        const value = $(el).attr('value');
        if (value) $(el).val(value);
    });

    $('a[method="post"], a[method="POST"]').click(function (e) {
        const action = $(this).attr('href');
        const csfTokenValue = $("meta[name='_csrf']").attr("content");

        // 동적으로 폼을 만든다.
        const $form = $(`<form action="${action}" method="POST"><input type="hidden" name="_csrf" value="${csfTokenValue}"></form>`);
        $('body').append($form);
        $form.submit();

        return false;
    });
});
