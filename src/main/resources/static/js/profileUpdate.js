
const profileUpdateForm = document.querySelector("#profileUpdateForm")
const submitButton = document.querySelector(".save-btn")

profileUpdateForm.addEventListener('input' , (e) =>{
    submitButton.disabled = false;
})

profileUpdateForm.addEventListener("submit" , async (e) =>{
    e.preventDefault();

    const redirect = confirm("변경사항을 적용 하시겠습니까?")
    if(redirect){
        const formData = new FormData(e.target);
        const formObj = Object.fromEntries(formData);

        const response = await fetch("/user/profile/update" , {
            method : "PATCH" ,
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(formObj)
        })
        if(!response.ok){
            const nickNameError = document.querySelector("#nickname-error")
            const emailError = document.querySelector("#email-error")
            const result = await response.json();
            console.log(result.status.nickname)
                nickNameError.innerHTML = "";
                emailError.innerHTML = "" ;
                nickNameError.textContent = result.status.nickname
                emailError.textContent = result.status.email


        }else{

            const result = await response.json();
            console.log(result)
            alert("수정되었습니다");
            window.location.href = "/user/profile";

        }
    }

})
