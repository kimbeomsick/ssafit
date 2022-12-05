<template>
    <v-container>
  
      
  
      <!--검색창 start-->
      <v-form @submit.prevent="search">
  
        <v-text-field label="검색어를 입력하세요" prepend-inner-icon="mdi-magnify" type="search" v-model="input">
        </v-text-field>
  
        <p class="font-weight-light">{{inputText}}</p>
        <v-divider></v-divider>
      </v-form>
      <!--검색창 end-->
      
  
  
      <!--결과창 start-->
      <v-row outlined v-for="{title, thumbnailPath, videoId, likeCnt} in searchVideoList" :key="videoId" >
        <!-- :to="{name : 'playlistDetail'}"  -->
  
        <v-col cols="12">
          <v-card height="150" hover @click="movePage({title, thumbnailPath, videoId, likeCnt, isLike})">
            <!--파라미터로 넘기는 것도 해야함 -->
            <v-row>
              <v-col cols="3" align-self="center">
                <v-img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxITEhUSEhMWFRUVGBgVFxUXFhUWGRUdFxcWFxUYGBcYHSggGBolGxUYITEiJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGxAQGzAlICUtLS0tMC4vMC0tLS0tLS0tLS0tLS0tLS0tKy0tLy8tLS0tLS0vLS0tLy0tLS0tLS0tLf/AABEIAL0BCwMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABAUCAwYBB//EAEMQAAEDAgMEBwQIBAUEAwAAAAEAAhEDIQQxQQUSUXEGImGBkaHRE1Kx8AcUFTJCU8HhFmJysiMkQ5LxgqLC0jNzg//EABsBAQADAQEBAQAAAAAAAAAAAAABAwQCBQYH/8QANREAAQMCAwYFAwMDBQAAAAAAAQACEQMhEjFBBFFhcZHwIoGhscEFE9EUMuFScvEGQpKisv/aAAwDAQACEQMRAD8A7hERSvMRERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERERCUWFXI8j8EUFQmbZoHKqPE+i3sx1M5PHiV8+wlVs966DCVmRmfJUVq+D9rSV9VW+g0aeRef+P4XRuxjBm5vmtX2pR98ccj6KkxlWnGagbswRx/QepUbNWdUdDxHlC8j6lsA2ah91k5gHEIsZuLDWPJdUNqUffHgfRefatH3x4H0XKhpTcW77Y3rwf1J3Lq/tWj748D6J9qUffHgfRcoGIGJ9sKf1J3Lq/tWj748D6J9q0ffHgfRcp7Ne7ijAE/Uncuq+1KPvjwPon2pR98eB9Fy24vNxMDVP6grqvtWj748D6Lz7Vo/mDwPouVLVoxhIY48AT3RfyQMBIC7ZVLnAFdiNq0fzG+J9FIoVmvG80yOPJcHRdLbQuX6QbcxVHEFtGvUY0Bp3Q47olonq5KqDqvo9v+lUqFLHTcTeLxlfgF9pRfFcF0/2gzOq2oOD2NPm0A+a6jZP0nMdAxNEs/npneHe03A5EpC8YsK+hIomzdpUa7d+jUa9usG45jMHmpaLhERERERERERERERERERERERERFhVyPI/BZrCrkeR+CKCvnODcJ0z1V1Sfb8PkuaY4ypdKqVmNLFn7L9GqHEb+ytsS4ELZQpWj5uP2UXB0XPMnLMn4q3bT9O4K6kIMr5X/Um1tbTGzNN5DncAAYnic+QnUKMKayZQlTW4fX5C2MokcD3cJ9VcXr5ENVcaKxFJXNFr40i9oEXibdy1ua7PU9gvz7clGNdFlpVfUw8ZxkDynQ9qw9mrMlwEWnlewgeS01ZJkx3AD4LrFKOaFANP5/RYYjqQSLFTKsgSBMdaM7CVSbTxxqEANkmyvo0TUIOmu/2K9v6ds2yOoF1UYnTGZEbst+cn8zYhkidF46kDYixst2FolrGzmAJ10yW3cWZxAJAXkuwhxAMiTB3ibHzC5mphnUjBEt0cPge1cL0mqTiHHsb8F9e9mvlfT1gbjXgCBusNh2Kx9YVBceLfPW3HWOi9entrqzBSdp33n7KjYVIpXUVin4VkrhQ9Wmx69Si8VKLi1w8D2EcF9R6OdJ214p1RuVeGj+1p/T45r5vgcMdPn1XRYPBBwAOeYix4gg6Fd4JXn1a2AyvpCKk2LtF0ihWPXjqPy9oBnycNR3q7VRBBgqxrg4SEREULpERERERERERERERERFi/I8isli/I8kRcWej7dN4cw0+ik4fYzBnPfAVicO8fi8CSvWvcNfFXfpGkS0g98l6P3PqFRvgrTyAB6hoPrPFY06AFgI5LHFyGy0SQRIGZH4o7clLa/jHzzCzDgq3bO+IXmt2WvTeKmCSDNxMnO+9eYStMHc3R7p/VW1LB70OHYYjwULD9l1aY+t7LDPqNsWgGR/UJWU03BwbkrWU8b3FwjWAIjksHhrTuFpvFxEdaY1mbLUG03ZOHI2jxVXsPaTq7faud1gRc5xvG0xoP04q2qMIlzQDJNpgET5rWNnbA3/K1HZqbm7lqr4IwO++nzZQKmEIBJU99MN3nEltpgWkkjumLRyUjEUiWgANy1MeFlxUY2m2SbclnfsBd+256fKoCxYex1AUuo8TEQfnVY747PNd06QqNxNuFnH02vN2qN7JPZrc8n5CgbRpPdAa0zNncO/RWjZv6jHfkrB9NLRiquDRG43O7RSDTXyP6RR/nn/0U/wC1fYfYO1PmV8g+kgf59/8ART/tVJY1t2mVGzUwypZwNtJ+VQ0Wq62fQnNU+FdddZsMNfbXh6LtglaqtgrnZODiDpx4c+ztXUtwMCRnmfjI8O/PMXhbOYGROSn1sWGdXQ/dP6TzWkCAvFrgkqPjGio2J3XNhwcM2OFw4K32HtA1qfWEVGHcqN4OGo7CII5xouVxeMh0jL5kcrg8ndiy2XtIU8Qx/wCGpFJ/f/8AE7uPVngVTVEiVGzTTdByPuu6REWdegiIiIiIiIiIiIiIiIixfkVksX5HkiKp9uT+ELMVBwZ5rCjXYRIHkLf9y3BzfdPgP/ZPvt0aR5/yvebTpNNqLx3/AHrnaG1jWqlrQGtGURJ7TKvaREhpic5y/RchV2RWpVDuiRPVdnI0mMiug2HhantPa1RkCGiBN8zyj4rdXdTAxMdkLCbn/sPO1lVS2h0faLDiBubiB6Hqeav8PT+Q4Ld0lfGArWmA21j+Nqyw7h/MPBOkgH1GvMxAkjP77V5g2hz6oad/H8kLuuIYc/X5JVD0Nok0RGW8IOptbeHLuXSMLmuE5EEiPP4lUnQ7EA4fdz61iNY48PnLJdJSZxyvFyDp89y3VHRM5KgSQCq+rXduyGzum2swdZU+owFrZA+6NY0WnEWEEW55gkzca5rzaWHe9rQwDIXmPBUbQRhGi7pGHSVXPoBzwWj8TRY2knKCLqY3DCR1WEcN1wnKQsdn7LNI7zyd7O2mYmdf+VMqlodDesOImAbQeHHzVTXYLNnr+bq+qQ9wsSO+K0+xZbqsJvI3TYCQD4qHi3gNgNbM5gEWUiviRzgkyAOFoOoUCrWBud4+Ck1mjQlT+npn9zSR3xUY1Xdnz3qONl4WuXfWKNOo50AEsa4iwycRPmpNSqzt7w1StiV4eXsyNpAad23WN88lNGqHPgNjvmVTW2ekxuJtMt46f+iuE6U/RzTZL8ISDf8AwXEmYE9RxuDE2PDS08fg3OYZyLT8F9pr0QS78RIkkj7w487acF88+k3ZP1auyo2za4JFwbsgE24hzTz3lrc0NWM3UqhtcPpzkcj2FQ6m0SWxNx8/suXwOOgkcVv+tX5/P6IXysr6IBVzVxYInv8AnunyUepWlpHzxHmAq1uI+fNeNr/PJcErOaS+w9Hsf7fDsqakQ7+ofeVmuJ+i/ETSrs92oHf7mx/4LtlSVYiIihEREREREREREREWFXI8is1hVyPI/BEXBYLaD2vJ3HEEZWztBz5qzp7Y/kd5KlpvMXHxPgpLagz4LRU2em43HqdLadP5sPrTF/EZudPkd+9w3a4/LPkt9LapOVN9s8lX4PDF5AMgHWDHIHjp38FcuoMY5rBuAXG8bgzxM5xqAsrtnpxYepXLnNacJdPRWWz6m9d0t7DE5SFt6RkOwNcD3R/c1VTKhnqwYkTOYBAnvkKy2hS3cFU9sOq7dJEhtt5uuiop0AyoCG+qxbRBYbrmuhjiygQbQ93blpGWi7SiHOzygG/aqPoZhyMPJb1t5wcHAjd60tIBztB0tC6WG726HAmOyR4aXXoVHA5LKCSZULGlgad/tiBN4sI1vPDNTi8BoBB3oFvBa6ldsg9V0SIyHDhY2AWrHYmG5CSOOXJUVQHNAIXTB4lorbQptM1N4Hd6oN5zv8FSVtttgdV8xew5ceCjbTxuhBMAAE8IuPP4qndiAe9cs2Zrhkt7AwTJv3w8udlZVtrN91/gPVRam2R7j/Aeqhvqd/w/dR3v7QO9Wt2Wnx6rRiiwPoP4HU/xnitol7h1Xbo0tc+K6PotiQ5rjuRfWNIHfmVybnHTxGq6To1V/wAIg2u4g8NP1VjaLAfCPX4hZ9ue40g079w4roaFQh/Xs0jNt44SNRbzXC/TVVHscPa/tHedOT8B4ruMGx+bn392MxaSTI0C+S/TH0iGIxTKLfu4drmug/jeRvD/AKQ1o5kqajl5YFlxVCt1gpQrXVXSddb2vVUrlwVi2t89y9FX9VAa9ZtekqlzV9L+iV8uxP8A+fxqL6Kvn30QUv8ACxFT3ntb/taSf7wvoK4KzuzRERFCIiIiIiIiIiIiLCrkeR+CyWFY9U8j8EUL5vTrNOpyz8bfOatdg4Z1Zx3TIa0udcSIgGAY3jeY7O5UeDqtbbeMROhmREH5kK2wWIa1rjvO63AwWgwO/v8ABbSDBjfwPt3JEaFfT1Kj3ZOGIX6H+7LjHTMXrcP1S4PG51+qA4GAZbHaYbci1hKzFcbzZgNpu3zvHqjeOWRygjtLtJVSzab3T1nFxkEgkTJO9a8AwCedhmsBWcS45CN1wI3t3JxILp3TN5zuVxhBsT8fj5WMVMQiDew06ZAacrnIK/p4hu9LiXS0vLB1AJOnvcPBW21Kh+p1ZNgAM2ndEtmJEZHVcjsus3eAcCd3qgknLQcuyV1e0BOArAD8NhlqPJcOb4wDqofLqcyO+/NRej+LotpllEvczekPf97IAiTnlGloVk3HdYBskwZjui3G/muQ6P1G+y61gHCAdSc4A0Hmr2riWsbvZwYJGvhKuNIWgZqkyLFSMRtKGnQgnQkTPVOaj7XxJ3WAZ7o+H7+a0YjEAj7sxDiMs85GqrdpYvfaC0TAgBcvpzAA15q7ZmFzpOUactFX4+q6SDxy7rz3qIcSIgm2VuWvkvGkudJMdaPh280rMbG9o6c5tBHbrn3KIiGkZ9/B4CM5haG/dMvbYH+rdvteNIi1uJGsPJnMxf7uucLyrUcBz0g3/cLCCG7wmLyYy+QtVRhgTIn3shMqTBtN++MTeeHQqaTyBkZi98gcrye7ZSFlUM3F54mP+fNXGxcYGUyajg1lzL3QBGYv2kKhIkt62t+wc/nNcT0rj6y7XqtieSOfhGXXv4XG0w6mDy9vTjJN19C6TfSUxlN1LBnfquzr3DKef3Gn77gDnlrfJfJ6jyTJMk3JNyZzJOpXhK8WYklYlm0rNrlpWQKhQVvDlsDlHBVp0d2W7FYilQb+Nw3j7rRd57mg98IuCvsf0c4D2WApSL1Jqn/r+7/2Bi6ZY02BoDQIAAAHACwCyULCTJlEREREREREReFEXhKwL1i8rS96KQFtNRaa9XqnkfgtTqqi4mt1TyKKVxGBpyXmIAi5uR+imNoAknf6rpm15Od1Cwpkbu8Z7bg81k1wALXugg5ATvd62VBDiC4tvkBoQOBkzrkNYX04ZSwND2gi5mQPFcxIIi3C8WsAFZ1KbWusN4PAOZtGvaCpFKqwloltx1zFwYz5SbKllx74z7YyPOFnDgRBE5yOzj4BGzgDXTInuN+7K4WZ1RwDixnhnXIA55RrO+ASBFl0mDaJbBEdnK3acl1OGrU3U/Zud97QRxmF88wFRwne6tpBjlIjlw4qwp7UAIcJFjqOHD99Ee0uMgR7Tp3+VW1gdTxugchut5Zcp5qVtdtGnV9lRMACSCbTrmoQxFs7aRlnKhYisKhLjMnM8JWs1GwSSQ2fgRl2ZHuV4LsMkzbuewu6ZwtEQRnMxfjy88+tl7eCDnvGD1iI1sBncTKj1QOs5pmASRftsf3Uao2A2NTOcnKSO7ivMJU3TJOs58on50XJxSc+vDIDLoJtJlQSKhDcIGs3tbkJk6ZTOmWyrTJzETlPZlbVaHM63nzUzEV2niYJgza+a0Oc1w3u3KIgXkZ3nsUB5wgvbB662y7m11a/ZWYoaQXcbTGeVuJibaxK8ZWZYdWCTvSHQOy/Ja4DnQLAugP7AbdX3o81HfMxocuA8eZWuoHNtIM3kXy4FcYcBMWtplr/AIHADcgqOw3aIB0Ee+8axYnMSptfBNmGu7TI+H7rgOl0fWXbuW634Lt4qgSWkznrx0XBdJz/AJg/0t+CzvHhHjxd3m2c5cOabWWhsNZhk6z6ac7Z8lVSsgFiFsaFUvOXoasw1eNWalcEryF9i+i3o2aFE4mq2KtYdUEXZTzHIuMHkG9q5f6PeiPt3NxOIb/gtMsYf9Ujj/IPPLKV9gD0Kz1X/wC0LJElFCoREREREREReEL1ERR3qJWVkWqPVoSimVS16sKBi8RY8lbYvBOXPbRouaCTkoVguqB9oIgjjlktxaSWuOR04aAxrlK0MqEGDAPLLj8VKa+4AgwM48InS69AuxHfuytzMWm3PyXvhrTMmBOXHlJJ+N91hUrE5WAy8ZFtLL2i57nQAb52PZcxzWvfBtrqTbmvcLXLS4NJklpDgSIDTfmCoql4/Znu/O4cM+C4cwuMkm8T0y/nJWuHIBOdpE8MrjgbeajY6sxzt4GXHPTS57CtJqlxJmHSTEQACL/PavGVh947ptluxHebwuHUTjxXm3vrOmuYAutdTDUZggC8ydBvF5kZRebZStlCpTgNO8ZkQIAknMknLdA0zWJdSiAHzJNyCCADa0WJDSeZHaouGrG4tcjyss3kSTHEW0nIwVP25HfcrzTQGHETztl35LbWqjqw0g8d6ZEEEkXuSCbGB5rP60BaxyzE5W7lpOFDRLySfclaurIMWvIJn/m66aPCbSN5/wAq9lJ1A3IDrZ6c9OYmd6k4p4jhrAUepVgkDIrNjWkOP3e62WgXv1cbpcTxiJz0lS4mbDp8HVcuYSS4Rv3Ze3Tcsa4e0AEEW1B5j9VtwjyA51ich2DXkmNrvdmZkDkI7OPqvBhoEybgXGXhqs8ugNffhodVbTmm4lpy36Zgd6eSwbLj2lcT0sBGJdOcN+C7xzCABNo017e9cJ0uP+ZP9LfgrK7y5gPp17hZqxbEaz3/AJ/CpgtjSs8PhHv+63xIA81fbN6LOfepUa0cGnePjkPNZFkcQFRsBJAAJJsABJPYAM13fRXoTJFXFiwuKPH/AOwjT+Ud/BW2xNkUaF6bBve+buPfoOwQugokoqHP3K1ovAAAgAWAFgOAAUllRV1IFTKbSioUtj1uDlGYwre1qKCs0REUIiIiIiIiIiIiJC018Kx4hzQQdCFuREVK/orgyZNFvifVYjopg/yh4n1V4iLrG7eVSfwrhPyW+fqvf4Vwf5LfP1V0iSmN29U46M4T8lnn6p/DOE/JZ5+quERMbt56qoHRnCfkM817/DeE/Jb5+qtkUymJ29VDujeFP+izz9VgejGE/Jb5q6RRKYnb1SHovhfyx5+qwPRXDe55n1V8iSmN29c+eiWG9zzPqvP4Sw+W6f8Ac71XQoiY3b1zo6I4b3fN3qtrOi+GH+k3mQD8VeoiF7jqqlvR+gP9Nv8AtC2t2PSGTG+AViiKMRUJuz2D8I8FubhWjRb0RRK1ikOCzDV6iIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIv/2Q=="  height="130" width="100%" class="ml-2">
                </v-img>
              </v-col>
  
              <v-col cols="9" align-self="center">
                  <h4>{{title}}</h4>
                  <br>
                    <v-icon left color="red">
                      mdi-heart
                    </v-icon> LIKE {{likeCnt > 0 ? likeCnt : 0}}
              </v-col>
  
              
  
            </v-row>
          </v-card>
        </v-col>
      </v-row>
  
      <!--결과창 end-->
  
    </v-container>
  </template>
  
  <script type="module">
  import { mapState } from 'vuex'
  export default {
    name: 'searchResultView',
    data() {
      return {
        input: "",
        inputText:""
      }
    },
    methods: {
      search() {
  
        if (this.input === "") {
          this.inputText= '아무것도 입력 안함'
        } else {
          this.inputText= ` "${this.input}" 검색 결과`
          this.$store.dispatch("searchVideo",this.inputText+"운동")
        }
      },
      movePage(video){
        console.log(video)
      let userId = JSON.parse(sessionStorage.getItem('loginUser'))['userId']
  
        this.$store.dispatch("saveVideo",video) // 저장하기
        this.$store.dispatch("getVideo",video.videoId) // 바로 가져오기 
        this.$store.dispatch("getLikeVideo",userId)
        this.$router.push({name:'videoDetail'})
      }
    },
    computed:{
      ...mapState(['searchVideoList','searchText'])
    }
  }
  </script>
  
  <style>
  
  </style>