export function interceptorPromise(resolve, reject, resp, func) {
    if (resp.status >= 200 && resp.status < 300) {
        //console.log(resolve);
        resolve(resp);
        func(resp);
    //} else if (resp.status == 401) {
    //    window.location.href = '/sign/in';
    } else {
        // TODO 全局错误处理
        if ('production' !== process.env.NODE_ENV) {
            console.warn(resp.status + ': ' + resp.text);
        }
        reject(resp);
    }
}

export function interceptor(resp, func) {
    new Promise((resolve, reject) => {
        if (resp.status >= 200 && resp.status < 300) {
            resolve(resp);
            func(resp);
        //} else if (resp.status == 401) {
        //    window.location.href = '/sign/in';
        } else {
            // TODO 全局错误处理
            reject(resp);
            console.warn(resp.status + ': ' + resp.text);
        }
    });
}