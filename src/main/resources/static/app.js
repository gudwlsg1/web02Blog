let user;
let editMode = false;
let parentId;

$(async function () {
   await login();
   await init();
});

const init = ()=> {
   getData();
};

const login = (userId, pw)=> {


   let id = 1;
   userId = "nqads78";
   pw = "d404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db";

   let data = {
      id : id,
      account : userId,
      userPassword : pw
   };

   $.ajax({
      url: '/user/login',
      type : "POST",
      dataType : "json",
      async : true,
      contentType : "application/json",
      data : JSON.stringify(data),
      success : (response) => {
         console.log(JSON.stringify(response));
         setUserInfo(response.data)
      },
      error : (err) => {
         console.log(JSON.stringify(err));
      }
   });
};

const getData = (command) => {
   $.ajax({
      url: "/post",
      type: "GET",
      async : true,
      success: (response) => {
         console.log(response);
         setPost(response.data);

         if(!command || command === null || command === undefined){
             setUL(response.data);
         }

      },
      error: (err) => {
         console.log(err);
      }
   });
}

const setPost = (data) => {
   if(!data || data === null || data === undefined){
        return null;
   }

   let post = !data[data.length-1] ? data : data[data.length-1];

   if(post === null || !post || post === undefined){
       return;
   }

   let div = $('#main-content')[0].children[0];
   div.setAttribute("id",post.id);

   $('#writingTitle').text(post.title);
   $('#writingName').text(post.name);
   $('#writingTime').text(post.created);
   $('#writingContent').text(post.content);
};

const setUserInfo = (data) => {

    user = data;

    if(data === null || data === undefined || !data){
        return ;
    }

   $('#rightAside').html(`<div style="padding: 20px">
            <div>사진</div>
            <div style="font-size: 18px">ID : ${data.account}</div>
            <div style="font-size: 20px">이름 : ${data.name}</div>
            <div style="font-size: 12px">가입일: ${data.joined}</div>
            <div style="font-size: 12px">게시물 수 : ${data.count}</div>
            </div>`);
};

const showPost = () => {
   $('#post-container').show();
}

const openFileDialog = ()=>{
    $('#uploadImg').click();
}

const btnPost = () => {

    if(!user || user === undefined || user === null){
        alert("로그인 후 이용해주세요!");
        btnCancle($("#post-container"));
        return;
    }

    if(editMode){

        if(parentId <= -1){
            return;
        }

        let editData = {
            title : $('#inputTitle').val(),
            content : $('#inputContent').val(),
            userId : user.id
        };

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            url: "/post/" + parentId,
            data : JSON.stringify(editData),
            dataType : "JSON",
            success : (response) => {
                btnCancle($("#post-container"));
                clearPostContainer();
                setPost(response.data);
                editMode = false;

                let ulId = '/post/' + parentId;
                let ul = document.getElementById(ulId);
                ul.innerHTML = response.data.title;

                console.log(ul.innerHTML);

                parentId = -1;
            },
            error : (err) => {
                console.log(JSON.stringify(err));
            }
        });

        return;
    }

    let file = $('#uploadImg');

    // if(file[0].files.length === 0){
    //     alert("사진 선택하세요");
    //     return;
    // }else {
    //     file = $('#uploadImg')[0].files[0];
    // }
    let formData = new FormData();

    formData.append("uploadFile",file);
    let imgInfo;

    if(!file[0].files.length === 0){
        console.log("file");
        $.ajax({
            type : "POST",
            data : formData,
            url : "/attachment",
            processData : false,
            contentType : false,
            success : function (response) {
                imgInfo = response;
                console.log(JSON.stringify(imgInfo));
            },
            error : (err) => {
                console.log(err);
            }
        });

    }

    let data = {
        title : $('#inputTitle').val(),
        content : $('#inputContent').val(),
        userId : user.id
    };

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "/post",
            data : JSON.stringify(data),
            dataType : "JSON",
            success : (response) => {
                btnCancle($("#post-container"));
                setPost(response.data);
                clearPostContainer();
                setUL(response.data);
            },
            error : (err) => {
                console.log(JSON.stringify(err));
            }
        });
};

const btnCancle = (element) => {
    element.hide();
};

const clearPostContainer = () => {
    $('#inputTitle').val("");
    $('#inputContent').val("");
};

const btnEdit = (btn) => {

    let parent = btn.parentElement.parentElement;
    parentId = parent.id;

    $('#inputTitle').val(parent.querySelector("#writingTitle").innerHTML);
    $('#inputContent').val(parent.querySelector("#writingContent").innerHTML);

    editMode = true;

    showPost();
};

const btnDelete = (btn) => {

    let parent = btn.parentElement.parentElement;
    parentId = parent.id;

    $.ajax({
        type : "DELETE",
        url : "/post/" + parentId,
        success : () => {
            getData("delete");
            DeleteUL();
        },
        error : (err) => {
            console.log(err);
        }
    });
};

const setUL = (data) => {
    if(data === undefined || !data || data === null || data.length <= 0){
        return;
    }

    if(!checkArray(data)){
        $('#writingPost').prepend(`<li>
                        <a id="/post/${data.id}" style="font-size: 12px;"  onclick="getPostData(this)">
                            ${data.title}
                        </a>
                    </li>`);
    }

    for(let i = 0; i < data.length; i++){
        $('#writingPost').prepend(`<li>
                        <a id="/post/${data[i].id}" style="font-size: 12px;" onclick="getPostData(this)">
                            ${data[i].title}
                        </a>
                    </li>`);
    }
};

const checkArray = (data) => {
    return Array.isArray(data);
}

const getPostData = (data) => {

    let url = data.id;

    $.ajax({
        type : "GET",
        url : url,
        success : (response) => {
            setPost(response.data);
        },
        error : (err) => {
            console.log(err);
        }
    });
};

const DeleteUL = ()=> {
    let ulId = '/post/' + parentId;
    let ul = document.getElementById(ulId);

    ul.parentElement.remove();

    parentId = -1;
}