<template>
<div class="editor">
  <!-- <div v-if="editor">
    <input
    type="button"
     @click="editor.chain().focus().toggleCodeBlock().run()" 
     class="code-block-btn"
     :class="{ 'is-active': editor.isActive('codeBlock') }"
     value="코드입력"
     >
     <input class="code-btn" type="button" value="코드저장" @click="codesave">
  </div> -->
  <editor-content  :editor="editor" />
</div>
</template>

<script>
import { Editor, EditorContent, VueNodeViewRenderer } from '@tiptap/vue-2'
import Document from '@tiptap/extension-document'
import Paragraph from '@tiptap/extension-paragraph'
import Text from '@tiptap/extension-text'
import CodeBlockLowlight from '@tiptap/extension-code-block-lowlight'
import CodeBlockComponent from './CodeBlockComponent.vue'
// import HardBreak from '@tiptap/extension-hard-break'
// load all highlight.js languages
import lowlight from 'lowlight'

// load specific languages only
// import lowlight from 'lowlight/lib/core'
// import javascript from 'highlight.js/lib/languages/javascript'
// lowlight.registerLanguage('javascript', javascript)
import { api } from '../../../../api.js'
import axios from 'axios'
export default {
  components: {
    EditorContent,
  },

  props: {
    item_pk: Number,
  },

  data() {
    return {
      editor: null,
      editable: false,

      code: '',
    }
  },

  beforeUnmount() {
    this.editor.destroy()
    // this.editor.getHTML()
    // this.editor.getText({ blockSeparator: "\n\n" })
  },

  created() {
    const token = localStorage.getItem('jwt')
    const itempk = this.item_pk
    axios({
      url:  api.GET_FREE_BOARD_DETAIL + itempk,
      method: 'GET',
      headers: {
        Authorization: 'Bearer ' + token
      },
    }).then((res)=>{
      // console.log(res.data)
      // this.code = res.data.cboardCode.split('\n').join('<br />');
      // this.code = res.data.cboardCode.replace(/(\n|\r\n)/g, '<br>');
      // this.code = res.data.cboardCode.replace(/(?:\r\n|\r|\n)/g, '<br />')
      this.code = res.data.cboardCode
      // console.log(this.code)
      this.editor = new Editor({
        editable: this.editable,
        extensions: [
          Document,
          Paragraph,
          Text,
          // HardBreak
          //   .configure({
          //     HTMLAttributes: {
          //       class: 'my-custom-class',
          //     },
          //     keepMarks: false,
          //   }),
          CodeBlockLowlight
            .extend({
              addNodeView() {
                return VueNodeViewRenderer(CodeBlockComponent)
              },
            })
            .configure({ lowlight }),
          
        ],
        content: this.code,
        parseOptions: {
          preserveWhitespace: 'full',
        },
        // onUpdate: ({ getHTML }) => {
        //         this.$emit("editorContent", getHTML());
        // },
      })
      // console.log(this.editor.options.content)
      this.editor.chain().focus().setCodeBlock().run()
    }).catch((err)=>{
      console.error(err)
    })
  },
}
</script>

<style lang="scss">
/* Basic editor styles */
.ProseMirror {
  > * + * {
    margin-top: 0.75em;
  }

  pre {
    background: #0D0D0D;
    color: #FFF;
    font-family: 'JetBrainsMono', monospace;
    padding: 0.75rem 1rem;
    border-radius: 0.5rem;

    code {
      color: inherit;
      padding: 0;
      background: none;
      font-size: 0.8rem;
    }

    .hljs-comment,
    .hljs-quote {
      color: #616161;
    }

    .hljs-variable,
    .hljs-template-variable,
    .hljs-attribute,
    .hljs-tag,
    .hljs-name,
    .hljs-regexp,
    .hljs-link,
    .hljs-name,
    .hljs-selector-id,
    .hljs-selector-class {
      color: #F98181;
    }

    .hljs-number,
    .hljs-meta,
    .hljs-built_in,
    .hljs-builtin-name,
    .hljs-literal,
    .hljs-type,
    .hljs-params {
      color: #FBBC88;
    }

    .hljs-string,
    .hljs-symbol,
    .hljs-bullet {
      color: #B9F18D;
    }

    .hljs-title,
    .hljs-section {
      color: #FAF594;
    }

    .hljs-keyword,
    .hljs-selector-tag {
      color: #70CFF8;
    }

    .hljs-emphasis {
      font-style: italic;
    }

    .hljs-strong {
      font-weight: 700;
    }
  }
}
</style>

<style scoped>
.editor {
  width: auto;
}

.code-block-btn{
  border: 1px solid black;
  border-radius: 0.5rem;
  background-color: white;
  font-weight: bold;
  font-size: 0.78vw;
  padding: 0.5vh 0.5vw 0.5vh 0.5vw;
  margin: 0 1vw 0 0;
}

.code-btn{
  border: 1px solid black;
  border-radius: 0.5rem;
  background-color: white;
  font-weight: bold;
  font-size: 0.78vw;
  padding: 0.5vh 0.5vw 0.5vh 0.5vw;
  margin: 0;
}
</style>