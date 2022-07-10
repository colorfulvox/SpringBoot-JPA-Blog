let index = {
    init: function () {
        $("#btn-save").on("click", () => { // ()=>{} let index의 this를 바인딩 하기 위해서 쓰인다.
            this.save();
        });
    },

    save: function () {
        //일단 각 id의 값들을 받아온다.
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        // ajax 호출
        // ajax 통신을 사용하여 3개의 데이터를 json으로 변경한뒤 insert요청을 할것이다.
        // ajax는 호출시 default가 비동기 호출
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // 자바에게 보내기전에 JSON으로 변환해서 보냄 [http body 데이터]
            contentType: "application/json; charset=utf-8", // http body 데이터가 어떤 타입 지정
            dataType: "json" //요청을 서버로 하고 응답이 왔을때 받을 데이터 타입 지정 [기본적으로는 버퍼이기에 String]
            // 이렇게 지정하면 버퍼 문자열이 json형태이기에 javascript타입으로 지정해준다.
            // 지금은 ajax가 알아서 파싱을 해주기에 굳이 안적어줘도 된다.

        }).done(function (response) { // 성공시
            console.log(response)
            location.href = "/blog" // 성공시 이동할 브라우저

        }).fail(function () { // 실패시
            alert(JSON.stringify(error));
        });
    }
}

index.init();