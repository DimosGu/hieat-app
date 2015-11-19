const Utils = {
    REGEX: {
        QQ: /\d{5,12}/,
        BIRTHDAY: /\d{4}-\d{2}-\d{2}/,
        DIGITS: /\d+/,
        EMAIL: /^[\w\-\.]+\@[a-zA-Z0-9\-\.]+\.\w{2,3}$/,
        PHONE: /^1\d{10}$/,
        SMS_CODE: /\d{6}$/
    }
};

export default Utils;
