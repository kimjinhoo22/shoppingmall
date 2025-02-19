export const validationRules = {
    userId: {
        regex: /^[a-zA-Z][a-zA-Z0-9]{3,20}$/, // 아이디: 영문, 숫자 4~12자
        errorMessage: "아이디 : 영문, 숫자로 4~20자여야 합니다.",
        status : false,
        validateErrorMessage : "아이디 : 중복된 아이디가 존재합니다."
    },
    password: {
        regex: /^(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,20}$/, // 비밀번호
        errorMessage: "비밀번호 : 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.",
    },
    email : {
        regex : /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
        errorMessage : "이메일 : 이메일 형식을 올바르게 입력해주세요.",
        status : false,
        validateErrorMessage : "이메일 : 중복된 이메일이 존재합니다."
    },
    nickname: {
        regex: /^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$/, // 이름: 한글 2~5자
        errorMessage: "닉네임 : 닉네임은 특수문자를 제외한 2~10자로 입력해주세요.",
        status : false,
        validateErrorMessage : "닉네임 : 중복된 닉네임이 존재합니다."
    },
    question : {
        required : true,
        errorMessage : "질문 : 보안 질문을 선택해주세요."
    },
    answer : {
        regex : /^[가-힣]{2,20}$/,
        errorMessage : "답변 : 답변을 1~20자로 입력해주세요."
    },
    address : {
        required: true,
        errorMessage : "주소 : 주소를 검색해주세요."
    }
};

