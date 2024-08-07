// 导入
import axios from 'axios'

axios.defaults.baseURL = 'http://127.0.0.1:18081'

axios.defaults.headers.common['Authorization'] =
  'AUTH_TOKEN'
axios.defaults.headers.post['Content-Type'] =
  'application/json; charset=utf-8'
axios.defaults.headers.put['Content-Type'] =
  'application/json; charset=utf-8'
axios.defaults.headers.delete['Content-Type'] =
  'application/json; charset=utf-8'
// 'application/x-www-form-urlencoded'

// 实例化对象
const instance = axios.create({
  // 完成对实例配置
  // `baseURL` 将自动加在 `url` 前面，除非 `url` 是一个绝对 URL。
  // 它可以通过设置一个 `baseURL` 便于为 axios 实例的方法传递相对 URL
  // 填写所有后台控制器的基地址，  理论上是项目名

  baseURL: 'http://127.0.0.1:18081',
  // 自定义请求头
  headers: {
    // 定义put提交的数据的格式 为JSON
    'Content-Type': 'application/json; charset=utf-8'
  }, // `timeout` 指定请求超时的毫秒数。
  // 如果请求时间超过 `timeout` 的值，则请求会被中断
  timeout: 3000, // 默认值是 `0` (永不超时),
  // `withCredentials` 表示跨域请求时是否需要使用凭证
  withCredentials: false, // default
  // `responseType` 表示浏览器将要响应的数据类型
  // 选项包括: 'arraybuffer', 'document', 'json', 'text', 'stream'
  // 浏览器专属：'blob'
  // 收到的字符串自动转换为JavaScript对象
  responseType: 'json', // 默认值收到的
  // `responseEncoding` 表示用于解码响应的编码 (Node.js 专属)
  // 注意：忽略 `responseType` 的值为 'stream'，或者是客户端请求
  // Note: Ignored for `responseType` of 'stream' or client-side requests
  responseEncoding: 'utf8' // 默认值
})

// 添加请求拦截器
instance.interceptors.request.use(
  function (config) {

    //获得token
    const token = sessionStorage.getItem('token')
    //每一次axios请求所使用的config
    config.headers.Authorization = token
    // 在发送请求之前做些什么
    console.log(
      '..........'
    )
    return config
  },
  function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
  }
)

// 添加响应拦截器
instance.interceptors.response.use(
  function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    console.log('收到了响应')
    return response
  },
  function (error) {

    console.log(error)
    return Promise.reject(error)
  }
)

// 创建实例后修改默认值
// instance.defaults.headers.common['Authorization'] = AUTH_TOKEN;

// 默认导出

export default instance
