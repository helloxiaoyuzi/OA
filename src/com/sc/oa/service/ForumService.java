package com.sc.oa.service;

import com.sc.oa.base.DaoSupport;
import com.sc.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum>{

	/**
	 * 上移，最上面的不能上移
	 * @param id
	 */
	void moveUp(Long id);

	/**
	 * 下移，最下面的不能下移
	 * @param id
	 */
	void moveDown(Long id);

}
