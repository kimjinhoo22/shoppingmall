import { validationRules } from './validationRules.js';

const validationState = {
  userId : false,
  email: false,
  nickname : false
}
const errorState = {};
const inputs = document.querySelectorAll("input, select")

function updateErrorMessages(){
  const errorList1 = document.querySelector("#errorList1")
  const errorList2 = document.querySelector("#errorList2")

  errorList1.innerHTML = "";
  errorList2.innerHTML = "";

  Object.keys(errorState).forEach(fieldName =>{

    const inputField = document.querySelector(`[name="${fieldName}"]`)
    if (!errorState[fieldName]){
      inputField.classList.remove("input-error")
      return;
    }

    const li = document.createElement("li");
    li.textContent = errorState[fieldName];

    // 닉네임, 아이디, 비밀번호, 이메일 → errorList1에 추가
    if (["userId", "nickname", "password", "email"].includes(fieldName)) {
      errorList1.appendChild(li);
    } else {
      // 나머지 필드들 → errorList2에 추가
      errorList2.appendChild(li);
    }
    if (inputField) inputField.classList.add("input-error")
  })
}

inputs.forEach(input =>{
  input.addEventListener("blur" , async (e) =>{
    e.preventDefault();
    const fieldName = e.target.name;
    const fieldValue = e.target.value.trim()
    const fieldRule = validationRules[fieldName]

    if(fieldRule){
      errorState[fieldName] = null;

      if(fieldRule.required && !fieldValue){
        errorState[fieldName] = fieldRule.errorMessage;
      }

      if(fieldRule.regex && !fieldRule.regex.test(fieldValue)){
        errorState[fieldName] = fieldRule.errorMessage;
      }
    }
    
    if((fieldName === "userId" || fieldName === "email" || fieldName === "nickname") && !errorState[fieldName]){

      try{
          const data = {
            fieldName : fieldName,
            fieldValue : fieldValue
          }
          const response = await fetch("/register/check" , {
            method : "POST",
            headers : {
              "Content-Type" : "application/json",
            },
            body : JSON.stringify(data)
          });

          const result = await response.json();
          console.log(result)
          if(result.result){
            errorState[fieldName] = fieldRule.validateErrorMessage;
          }else{
            validationState[fieldName] = true;
          }

        }catch (error){
          console.error(error);
        }
    }
    updateErrorMessages();
  })
})




document.querySelector("#registerForm").addEventListener("submit" , async (e) =>{

  e.preventDefault();
  const inputsArray = Array.from(inputs);

  await Promise.all(inputsArray.map(input => {
    return new Promise(resolve => {
      input.addEventListener('blur', resolve, { once: true });
      input.dispatchEvent(new Event('blur'));
    });
  }));
  // 에러가 하나라도 존재한다면 연속적인 폼 제출 차단 ( 백엔드 서버 부하 )
  if (Object.values(errorState).some(error => error)) {
    return;
  }
  // userId , email , nickname 의 중복검사를 전부 완료한 상태인지 다시 확인
  const isValidationComplete = Object.values(validationState).every(status => status === true);
  // 완료하지 않았다면 폼 제출 차단
  if(!isValidationComplete){
    return;
  }
  const formData = new FormData(e.target);
  const formObj = Object.fromEntries(formData);

  try{
    const response = await fetch("/register" , {
      method : "POST",
      headers : {"Content-Type" : "application/json"},
      body : JSON.stringify(formObj)
    });

    if(!response.ok){
      const result = await response.json();
      console.log(result)
    }
    const result = await response.json();
    alert(`${result.userId} 님 회원가입을 환영합니다!`)
    const redirect = confirm("로그인 페이지로 이동하시겠습니까 ?")
    if(redirect){
      window.location.href = "/login";
    }
    else{
      window.location.href = "/home";
    }
  }catch(error){
    console.error(error)
  }
})



/*   */





