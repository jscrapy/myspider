package org.jscrapy.contrib.dedup;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jscrapy.core.TaskConfig;
import org.jscrapy.core.dedup.DeDup;
import org.jscrapy.core.request.Request;

/**
 * Created by cxu on 2015/6/22.
 */
public class EhCacheDedup extends DeDup {
    CacheManager cacheManager;
    Cache ehCache;


    public EhCacheDedup(TaskConfig taskid) {
        super(taskid);
        cacheManager = CacheManager.create();
        cacheManager.addCacheIfAbsent(getTaskConfig().getTaskFp());
        ehCache = cacheManager.getCache(getTaskConfig().getTaskFp());
    }

    @Override
    protected boolean isDup(Request request) {
        String fp = request.fp();
        Element el = new Element(fp, 1);
        el = ehCache.putIfAbsent(el);
        if(el==null){//非重复
            return false;
        }
        return true;
    }

    @Override
    public void close() {
        ehCache.removeAll();
    }
}