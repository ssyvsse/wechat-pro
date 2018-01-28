package com.ssyvsse.wechat.msg.resp;

import java.util.List;

import com.ssyvsse.wechat.msg.resp.entity.Article;

/**
 * @author llb
 *
 * @Date 2018年1月28日 下午10:59:51
 */
public class RespNewsMsg extends RespBaseMsg {

	/**
	 * 图文消息个数，限制为8条以内
	 */
	private int articleCount;

	/**
	 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
	 */
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		if (articles.size() == 0) {
			throw new NullPointerException("articles's size must at least 1.");
		}
		if (articles.size() > 8) {
			throw new IndexOutOfBoundsException("articles's size must not less than 8.");
		}
		this.articles = articles;
	}

	public RespNewsMsg() {
		this.msgType = "news";
	}

	public int getArticleCount() {
		return getArticles().size();
	}

	@Override
	protected void subXml(StringBuilder sb) {
		if (articleCount <= 0) {
			throw new NullPointerException("articleCount must at least one.");
		}
		sb.append("<ArticleCount>").append(articleCount).append("</ArticleCount>");
		sb.append("<Articles>");
		for (Article article : articles) {
			sb.append("<item>");
			if (article.getTitle() != null && !"".equals(article.getTitle())) {
				sb.append("<Title>< ![CDATA[").append(article.getTitle()).append("] ]></Title>");
			}
			if (article.getDescription() != null && !"".equals(article.getDescription())) {
				sb.append("<Description>< ![CDATA[").append(article.getDescription()).append("] ]></Description>");
			}
			if (article.getPicURL() != null && !"".equals(article.getPicURL())) {
				sb.append("<PicUrl>< ![CDATA[").append(article.getPicURL()).append("] ]></PicUrl>");
			}
			if (article.getUrl() != null && !"".equals(article.getUrl())) {
				sb.append("<Url>< ![CDATA[").append(article.getUrl()).append("] ]></Url>");
			}
			sb.append("</item>");
		}
		sb.append("</Articles>");
	}

}
