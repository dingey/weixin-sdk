package com.github.dingey.weixin.model;

import java.util.Arrays;
import java.util.List;

import com.github.dingey.weixin.model.CustomMessage.TextCustomMessage.Text;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
public interface CustomMessage {
    /**
     * @param touser 接收者openid
     */
    CustomMessage setTouser(String touser);

    public static TextCustomMessage text(String content) {
        return new TextCustomMessage(content);
    }

    public static ImageCustomMessage image(String media_id) {
        return new ImageCustomMessage(media_id);
    }

    public static VoiceCustomMessage voice(String media_id) {
        return new VoiceCustomMessage(media_id);
    }

    public static VideoCustomMessage video(String media_id, String thumb_media_id, String title, String description) {
        return new VideoCustomMessage(media_id, thumb_media_id, title, description);
    }

    public static MusicCustomMessage music(String media_id, String thumb_media_id, String title, String description, String musicurl, String hqmusicurl) {
        return new MusicCustomMessage(media_id, thumb_media_id, title, description, musicurl, hqmusicurl);
    }

    public static ArticleCustomMessage article(Article article) {
        return new ArticleCustomMessage(article);
    }

    public static MpnewsCustomMessage mpnews(String media_id) {
        return new MpnewsCustomMessage(media_id);
    }

    public static WxcardCustomMessage wxcard(String media_id) {
        return new WxcardCustomMessage(media_id);
    }

    public static class TextCustomMessage implements CustomMessage {
        @Getter
        @Setter
        private String touser;
        @Getter
        @Setter
        private String msgtype="text";
        @Getter
        @Setter
        private Text text;

        public TextCustomMessage(String content) {
            this.text = new Text(content);
        }

        @Data
        static class Text {
            private String content;

            public Text(String content) {
                super();
                this.content = content;
            }

        }

        public TextCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    public static class ImageCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype="image";
        private Media image;

        public ImageCustomMessage(String media_id) {
            this.image = new Media(media_id);
        }

        public ImageCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    static class Media {
        private String media_id;

        public Media(String media_id) {
            super();
            this.media_id = media_id;
        }
    }

    @Data
    static class Wxcard {
        private String card_id;

        public Wxcard(String card_id) {
            this.card_id = card_id;
        }
    }

    @Data
    static class Video extends Media {
        private String thumb_media_id;
        private String title;
        private String description;

        public Video(String media_id, String thumb_media_id, String title, String description) {
            super(media_id);
            this.thumb_media_id = thumb_media_id;
            this.title = title;
            this.description = description;
        }
    }

    @Data
    static class Music extends Media {
        private String thumb_media_id;
        private String title;
        private String description;
        private String musicurl;
        private String hqmusicurl;

        public Music(String media_id, String thumb_media_id, String title, String description, String musicurl, String hqmusicurl) {
            super(media_id);
            this.thumb_media_id = thumb_media_id;
            this.title = title;
            this.description = description;
            this.musicurl = musicurl;
            this.hqmusicurl = hqmusicurl;
        }
    }

    @Data
    static class Article extends Media {
        private String title;
        private String description;
        private String url;
        private String picurl;

        public Article(String media_id, String title, String description, String url, String picurl) {
            super(media_id);
            this.title = title;
            this.description = description;
            this.url = url;
            this.picurl = picurl;
        }
    }

    @Data
    public static class VoiceCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype="voice";
        private Media voice;

        public VoiceCustomMessage(String media_id) {
            this.voice = new Media(media_id);
        }

        public VoiceCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    public static class VideoCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype="video";
        private Media video;

        public VideoCustomMessage(String media_id, String thumb_media_id, String title, String description) {
            this.video = new Video(media_id, thumb_media_id, title, description);
        }

        public VideoCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    public static class MusicCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype="music";
        private Music music;

        public MusicCustomMessage(String media_id, String thumb_media_id, String title, String description, String musicurl, String hqmusicurl) {
            this.music = new Music(media_id, thumb_media_id, title, description, musicurl, hqmusicurl);
        }

        public MusicCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    public static class ArticleCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype = "news";
        private List<Article> news;

        public ArticleCustomMessage(Article article) {
            this.news = Arrays.asList(article);
        }

        public ArticleCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    public static class MpnewsCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype = "mpnews";
        private Media mpnews;

        public MpnewsCustomMessage(String media_id) {
            this.mpnews = new Media(media_id);
        }

        public MpnewsCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }

    @Data
    public static class WxcardCustomMessage implements CustomMessage {
        private String touser;
        private String msgtype = "wxcard";
        private Wxcard wxcard;

        public WxcardCustomMessage(String wxcard) {
            this.wxcard = new Wxcard(wxcard);
        }

        public WxcardCustomMessage setTouser(String touser) {
            this.touser = touser;
            return this;
        }
    }
}
