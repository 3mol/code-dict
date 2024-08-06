package cc.miaooo.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface WordOpenAiService {
  @SystemMessage("你是一名优秀英语翻译官，请帮助用户翻译和解释单词。")
  @UserMessage("""
          这部分是用户期望得到解释的词语：
          ```
          {word}
          ```
       """)
  Response<AiMessage> explain(String word);

  @SystemMessage("你是一名优秀英语翻译官，请帮助用户翻译和解释单词。")
  @UserMessage("""
          这部分是用户期望得到解释的词语：
          ```
          {word}
          ```
       """)
  TokenStream explainStream(String word);
}