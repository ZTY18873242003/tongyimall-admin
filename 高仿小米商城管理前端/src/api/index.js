import request from '../utils/request';

export const adminlogin = form => {
    return request.post('http://localhost:8081/Login/login',form);
};


// 用户管理
export const getUsers = query => {
    return request({
        url: 'http://localhost:8081/User/getUsers',
        method: 'get'
    });
};


// 商品管理

export const getGood = query => {
    return request({
        url: 'http://localhost:8081/Good/getGood',
        method: 'get',
        params: query
    });
};

export const getGoodByCategory = query => {
    return request({
        url: 'http://localhost:8081/Good/getGoodById',
        method: 'get',
        params: query
    });
};
export const getGoodByName = query => {
    return request({
        url: 'http://localhost:8081/Good/getGoodByName',
        method: 'get',
        params: query
    });
};
export const updGood = query => {
    return request({
        url: 'http://localhost:8081/Good/updGood',
        method: 'get',
        params: query
    });
};



export const getGoodPicByCategory = query => {
    return request({
        url: 'http://localhost:8081/Good/getGoodPicById',
        method: 'get',
        params: query
    });
};
export const getGoodPicByName = query => {
    return request({
        url: 'http://localhost:8081/Good/getGoodPicByName',
        method: 'get',
        params: query
    });
};


export const getSugFoot = query => { return request.post('http://localhost:8081/Suggest/getSugFoot',query)};


export const getSugBig = kind => { return request({
    url:'http://localhost:8081/Suggest/getSugBig',
    method: 'get',
    params: {kind :kind}
    })
};
export const delSuggBig = query => {return request.post('http://localhost:8081/Suggest/delSugBig',query);};
export const pushSuggBig = (id,kind) => {
    return request({
        url: 'http://localhost:8081/Suggest/pushSuggBig',
        method: 'get',
        params: { goodid:id,kind:kind}
    });
};





export const pushGood = query => { return request.post('http://localhost:8081/Good/pushGood',query)};

export const pushSuggFoot = query => { return request.post('http://localhost:8081/Suggest/pushSuggFoot',query)};

export const updSuggFoot = query => { return request.post('http://localhost:8081/Suggest/updSuggFoot',query)};

export const delSuggFoot = query => {
    return request.post('http://localhost:8081/Suggest/delSuggFoot',query);
};



export const delGood = query => {
    return request.post('http://localhost:8081/Good/delGood',query);
};
export const delMulGood = query => {
    return request.post('http://localhost:8081/Good/delMulGood',query);
};


export const getGoodPic = query => {
  return request({
    url: 'http://localhost:8081/Good/getGoodPic',
    method: 'get',
    params: query
  });
};

export const updGoodPic = query => {
    return request.post('http://localhost:8081/Good/updGoodPic',query
    );
};

export const getGoodCate = query => {
    return request({
        url: 'http://localhost:8081/category/getCategory',
        method: 'get',
        params: query
    });
};
export const updateGoodCate = form => {
    return request.post('http://localhost:8081/category/updateCategory',form);
};






export const fetchData = query => {
    return request({
        url: './table.json',
        method: 'get',
        params: query
    });
};


