<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("txtPhone").addEventListener("keyup", function(event) {
            inputPhoneNumber(event.target);
        });
    });
    
    function inputPhoneNumber( phone ) {
        if( event.keyCode != 8 ) {
            const regExp = new RegExp( /^[0-9]{2,3}-^[0-9]{3,4}-^[0-9]{4}/g );
            if( phone.value.replace( regExp, "").length != 0 ) {                
                if( checkPhoneNumber( phone.value ) == true ) {
                    let number = phone.value.replace( /[^0-9]/g, "" );
                    let tel = "";
                    let seoul = 0;
                    if( number.substring( 0, 2 ).indexOf( "02" ) == 0 ) {
                        seoul = 1;
                        phone.setAttribute("maxlength", "12");
                        console.log( phone );
                    } else {
                        phone.setAttribute("maxlength", "13");
                    }
                    if( number.length < ( 4 - seoul) ) {
                        return number;
                    } else if( number.length < ( 7 - seoul ) ) {
                        tel += number.substr( 0, (3 - seoul ) );
                        tel += "-";
                        tel += number.substr( 3 - seoul );
                    } else if(number.length < ( 11 - seoul ) ) {
                        tel += number.substr( 0, ( 3 - seoul ) );
                        tel += "-";
                        tel += number.substr( ( 3 - seoul ), 3 );
                        tel += "-";
                        tel += number.substr( 6 - seoul );
                    } else {
                        tel += number.substr( 0, ( 3 - seoul ) );
                        tel += "-";
                        tel += number.substr( ( 3 - seoul), 4 );
                        tel += "-";
                        tel += number.substr( 7 - seoul );
                    }
                    phone.value = tel;
                } else {
                    const regExp = new RegExp( /[^0-9|^-]*$/ );
                    phone.value = phone.value.replace(regExp, "");
                }
            }
        }
    }

    function checkPhoneNumber( number ) {
        const regExp = new RegExp( /^[0-9|-]*$/ );
        if( regExp.test( number ) == true ) { return true; }
        else { return false; }
    }
</script>
<style>
    #input1 {
    	width: 300px;
        height: 50px;
        border-top: none;
        border-left: none;
        border-right: none;
        border-bottom : 3px solid black;
    }
    .btn {
    	width: 100px;
        height: 55px;
        border-top: none;
        border-left: none;
        border-right: none;
        border-bottom : 3px solid black;
    }
    .container{
    	margin: 0 auto;
    }
</style>

</head>
<body>
<div class="container" style="width: 600px; height: 500px;">
	
	<form action="searchId.do" method="POST">
		<input id="input1" name="name" type="text" class="mt-5" placeholder="이름을 입력해주세요!" /><br>
		
			<div>
		 	<label for="tel">전화번호 : </label>
		  	<input type="text" id="txtPhone" name="tel" class="mt-5" style="text-align:center;" maxlength="13"
		  	placeholder="000-0000-00000" 
			pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}"/>
			</div>
			<div class="text-right">
			<button class="btn" style="height: 50px;">제출</button>
			</div>
	<div class="text-right" style="margin-top: 10px;">
		<a href="searchPwForm.do" class="btn" style="width: 140px; height: 50px; font-size: 16px;">비밀번호 찾기</a>
	</div>
	</form>
</div>
</body>
</html>