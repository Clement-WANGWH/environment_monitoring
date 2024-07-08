import { defineStore } from 'pinia'
import $axios from '../components/utils/axios'
import { ElNotification } from 'element-plus'


// import $axios from '../components/utils/axios'
// useStore 可以是 useUser、useCart 之类的任何东西
// 第一个参数是应用程序中 store 的唯一 id
export const useUserStore = defineStore('user', {
    state: () => {
        return {
            imageUrl: '',
            form: {
                id: '',
                username: '',
                avatar: ''
            },
        }
    },
    actions: {
        async onSubmit() {
            this.form.id = sessionStorage.getItem('id')
            this.form.username = JSON.parse(sessionStorage.getItem('user')).username
            this.form.avatar = sessionStorage.getItem("imgUrl")
            console.log(this.form);
            const path = '/user/uploadAvatar'
            const { data: res } = await $axios.post(path, this.form);
            if (res.code === 200) {
                console.log(res);
                sessionStorage.setItem('avatar', res.result.avatar)
                this.imageUrl = sessionStorage.getItem('imgUrl')
                console.log(this.imageUrl);
                // this.initAvatar()
                ElNotification({
                    title: '成功',
                    message: '头像已保存,正在审核！',
                    type: 'success',
                })
            } else if (res.code === 106) {
                ElNotification({
                    title: '失败',
                    message: '头像上传失败!',
                    type: 'error',
                })
            }
        },
        // async initAvatar() {
        //     this.form.username = JSON.parse(sessionStorage.getItem('user')).username
        //     const path = '/user/fingAvatarByUsername/' + this.form.username
        //     const { data: res } = await $axios.get(path)
        //     if (res.code === 200) {
        //         this.imageUrl = res.result
        //         console.log(this.imageUrl);
        //     }

        // }
    }
})