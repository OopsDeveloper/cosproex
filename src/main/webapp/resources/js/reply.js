// 모듈 패턴: 즉시 실행하는 함수 내부에서 필요한 메서드를 구성해서 객체를 구성하는 방식
// ajax contentType은 보내는 타입, dataType은 서버에서 반환하는 타입
console.log("Reply Module .....");

var replyService = (function () {

    function add(reply, callback, error) {
        console.log("add reply..............");

        $.ajax({
            type: 'post',
            url: '/replies/new',
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            },
        });
    }

    return {
        add: add
    };
})();
