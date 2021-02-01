var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function() {     // btn-save 버튼에 이벤트 리스너 추가
            _this.save();
        });
        $('#btn-update').on('click', function(){    // btn-save 버튼에 이벤트 리스너 추가
            _this.update();
        });
        $('#btn-delete').on('click', function(){
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
        };

        $.ajax({    // 등록
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href='/';   // 글 등록 성공하면 / 로 이동
        }).fail(function() {
            alert(JSON.stringify(error));
        });
    },
    update : function() {
        var data = {
            title: $('#title').val(),
            content: $('#author').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',    // HTTP Method 중 PUT 메소드를 선택 (@PutMapping 으로 선언해서)
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function() {
            alert(JSON.stringify(error));
        })
    },
    delete : function() {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href='/';
        }).fail(function() {
            alert(JSON.stringify(error));
        })
    }
};

main.init();