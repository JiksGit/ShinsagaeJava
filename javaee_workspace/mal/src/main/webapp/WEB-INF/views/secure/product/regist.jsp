<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>
	<%@ include file="../inc/head_link.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Preloader -->
  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="/static/admin/dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
  </div>

  <!-- Navbar -->
	<%@ include file="../inc/navbar.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
	<%@ include file="../inc/left_bar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품 등록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리>상품등록</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->


    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      
            <!-- 상품 등록 폼 시작 -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">상품 등록 폼</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form id="form1">
                <div class="card-body">
                	<!-- 카테고리 영역 시작 -->
	                  <div class="row">
	                    <div class="col-sm-6">
	                      <!-- Select multiple-->
	                      <div class="form-group">
	                        <label>상위 카테고리</label>
	                        <select class="form-control" id="topcategory"></select>
	                      </div>
	                    </div>
	                    <div class="col-sm-6">
	                      <div class="form-group">
	                        <label>하위 카테고리</label>
	                        <select class="form-control" name="subcategory.subcategory_id" id="subcategory"></select>
	                      </div>
	                    </div>
	                  </div>
                	<!-- 카테고리 영역 끝 -->
                  <div class="form-group">
                    <input type="text" class="form-control" name="product_name" placeholder="상품명 입력">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" name="brand" placeholder="브랜드 입력">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" name="price" placeholder="가격 입력">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" name="discount" placeholder="할인가 입력">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control" name="introduce" placeholder="간단소개 100자 이하 ">
                  </div>
				   <div class="form-group">
                       <select class="form-control" name="color" id="color" multiple="multiple">
                         <option>색상 선택</option>
                       </select>
	              </div>
				  
				  <div class="form-group">
                       <select class="form-control" name="size" id="size" multiple="multiple">
                         <option>사이즈 선택</option>
                       </select>
	              </div>
	              
                  <div class="form-group">
					<!-- 편집 시작 -->
			      	<textarea id="summernote" name="detail"></textarea>
					<!-- 편집기 끝-->
                  </div>
                  
                  <div class="form-group">
                    <div class="input-group">
                    
                      <div class="custom-file">                      
                        <input type="file" class="custom-file-input" name="photo" id="photo" multiple="multiple">
                        <label class="custom-file-label" for="exampleInputFile">상품 이미지 선택</label>
                      </div>
                      
                      <div class="input-group-append">
                        <span class="input-group-text">Upload</span>
                      </div>
                    </div>
                    
                    <div id="preview" style="width:100%;">
                    	미리보기
                    </div>
                    
                  </div>
                
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="button" class="btn btn-secondary" id="bt_regist">상품등록</button>
                  <button type="button" class="btn btn-secondary" id="bt_list">목록보기</button>
                </div>
              </form>
            </div>
            <!-- 상품 등록 폼 끝-->
        
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
	<%@ include file="../inc/footer.jsp" %>

  <!-- Control Sidebar -->
	<%//@ include file="../inc/right_bar.jsp" %>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
	<%@ include file="../inc/footer_link.jsp" %>
	<script src="/static/admin/custom/ProductImg.js"></script>
	<script>
	function printCategory(obj, list){
		let tag="<option value='0'>카테고리 선택</option>";
		
		for(let i=0;i<list.length;i++){
			if(obj=="#topcategory"){
				tag+="<option value='"+list[i].topcategory_id+"'>"+list[i].top_name+"</option>";
			}else if(obj=="#subcategory"){
				tag+="<option value='"+list[i].subcategory_id+"'>"+list[i].sub_name+"</option>";
			}else if(obj=="#color"){
				tag+="<option value='"+list[i].color_id+"'>"+list[i].color_name+"</option>";
			}else if(obj=="#size"){
				tag+="<option value='"+list[i].size_id+"'>"+list[i].size_name+"</option>";
			}
		}
		
		$(obj).html(tag);  // innerHTML=태그 동일
	}
	
	//비동기 방식으로 서버에 요청을 시도하여, 데이터 가져오기 
	function getTopCategory(){
		$.ajax({
			url:"/admin/admin/topcategory/list",
			type:"get",
			success:function(result, status, xhr){ //200번대의 성공 응답 시, 이 함수 실행
				console.log("서버로부터 받은 결과는 ", result);
				//화면에 출력하기 
				printCategory("#topcategory",result);
			},
			error:function(xhr, status, err){
			}
		});
	}
	
	function getSubCategory(topcategory_id){
		$.ajax({
			url :"/admin/admin/subcategory/list?topcategory_id="+topcategory_id,
			type:"get",
			success:function(result, status, xhr){
				console.log(result);
				printCategory("#subcategory",result);
			}
		});
	}
	
	function getColorList(){
		$.ajax({
			url:"/admin/admin/color/list",
			type:"get",
			success:function(result, status, xhr){
				printCategory("#color", result);
			}
		});
	}
	
	function getSizeList(){
		$.ajax({
			url:"/admin/admin/size/list",
			type:"get",
			success:function(result, status, xhr){
				printCategory("#size", result);
			}
		});
	}

	//크롬브라우저에서 지원하는 e.target.files 유사 배열은 읽기전용 이라서, 
	//개발자가 쓰기 가 안되므로, 배열을 하나 선언하여,담아서 처리
	//주의) 아래의 배열은, 개발자가 정의한 배열일 뿐이지, form태그가 전송할 컴포넌트는 아니므로, 
	//submit 시, selectedFile에 들어있는 파일을 전송할 수는 없다!!!
	//해결책? form태그에 인식을 시켜야 한다.. (javascript로 프로그래밍적 formData 객체를 사용해야 함)
	// HTML 작성된 기존 폼에서 텍스트 입력관련된 컴포넌트는 사용하되, 이미지 업로드 컴포넌트는 재설정해야 함..
	let selectedFile=[];
	
	function regist(){
		// 기존 폼을 이용하되, file 컴포넌트 파라미터만 새로 교체(selectedFile 배열로 대체)
		let formData = new FormData(document.getElementById("form1"));
		// 동기 방식 전송
		// JqueryAjax 자체에서 formData를 비동기방식으로 간단하게 사용할 수 있는 코들르 지원
		// 기존 photo 버리고, 우리가 선언한 배열로 대체
		// formData.append("email", "qwer@naver.com");
		// formData는 개발자가 명시하지 않아도, 디폴트로 multipart/form-data가 지정되어 있음
		
		formData.delete("photo"); // 기존의 photo 파라미터 제거하기 append의 반대
		
		for(let i=0; i< selectedFile.length; i++){
			formData.append("photo", selectedFile[i]);
		}
		
		// 파일마저도 비동기로 업로드 가능
		$.ajax({
			url : "/admin/admin/product/regist",
			type : "post",
			data : formData,
			processData : false,  /* form 이루는 대상으로, 문자열로 변환되는 것을 방지 (바이너리 파일 포함 때문) */
			contentType : false, /* 브라우저가 자동으로 content-type을 설정하도록 하는 것 방지 */
			success : function(result, status, xhr){
				alert("업로드 성공");
			},
			error : function(xhr, status, error) {
				alert("err");
			}
		});
	}
	   
	$(()=>{
	   $('#summernote').summernote({
		height:200,
		placeholder:"상품 상세 설명을 채우세요"
	   });
	   getTopCategory(); //상위 카테고리 가져오기 
	   getColorList(); //색상 목록 가져오기 
	   getSizeList(); //사이즈 목록 가져오기 
	   

	   
	   //상위 카테고리의 값을 변경시, 하위 카테고리 가져오기 
	   $("#topcategory").change(function(){
			getSubCategory($(this).val());
		});
	   
	   
	   //파일 컴포넌트의 값 변경 시 이벤트 연결 
	   $("#photo").change(function(e){
			console.log(e);
			//e.target.files 안에는 브라우저가 읽어들인, 파일의 정보가 배열유사 객체인 FileList에 담겨져 있다.
			
			let files=e.target.files;//배열 유사 객체 얻기
			
			//첨부된 파일 수 만큼 반복
			for(let i=0;i<files.length;i++){
				selectedFile[i]=files[i]; //읽기 전용에 들어있었던 각 file들을,우리만의 배열로 옮기자 
				
				//파일을 읽기위한 스트림 객체 생성 
				const reader = new FileReader();
				
				reader.onload=function(e){ //파일을 스트림으로 읽어들인 정보가 e에 들어있음 
					console.log("읽은 결과 ", e);		
					
					//개발자 정의 클래스 인스턴스 생성 container, src, width, height 
					let productImg = new ProductImg(document.getElementById("preview"), files[i]  ,e.target.result, 100,100);
				}
				reader.readAsDataURL(files[i]); //지정한 파일을 읽기
			}
	   });
	   
	   //등록버튼 이벤트 연결 
	   $("#bt_regist").click(()=>{		
			regist();
	   });
	   
	   //목록버튼 이벤트 연결 
	   $("#bt_list").click(()=>{		
			$(location).attr("href", "/admin/admin/product/list");
		});
	});
	</script>
	
</body>
</html>