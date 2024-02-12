import { ref } from 'vue'
import { defineStore } from 'pinia'
import { sendLiveSchedule } from '@/components/api/LiveAPI'
import { useMemberStore } from '@/stores/member'

export const useReadyLiveStore = defineStore('readyLive', () => {
  const store = useMemberStore()

  const liveFormData = new FormData()
  const productImage = ref()
  const imageName = ref()
  const liveProduct = ref({})
  const liveScript = ref('')
  const liveChatbot = ref([])
  const liveSchedule = ref({})

  const sendData = async () => {
    console.log(liveProduct.value)
    liveFormData.append('image', productImage.value)
    liveFormData.append('product', liveProduct.value)
    liveFormData.append('live', liveSchedule.value)
    liveFormData.append('chatbot', liveChatbot.value)
    //로그인되면 아래것은 헤더로
    liveFormData.append('member', store.member.id)

    await sendLiveSchedule(liveFormData)
  }
  return {
    store,
    productImage,
    imageName,
    liveFormData,
    liveProduct,
    liveScript,
    liveChatbot,
    liveSchedule,
    sendData
  }
})
