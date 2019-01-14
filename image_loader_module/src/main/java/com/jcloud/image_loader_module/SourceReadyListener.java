package com.jcloud.image_loader_module;

/**
 * 通知准备就绪
 * modified by soulrelay
 */
public interface SourceReadyListener {

    void onResourceReady(int width, int height);
}
