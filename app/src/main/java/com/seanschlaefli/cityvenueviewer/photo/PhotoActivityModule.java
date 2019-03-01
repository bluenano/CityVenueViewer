package com.seanschlaefli.cityvenueviewer.photo;

import com.seanschlaefli.cityvenueviewer.bookmark.BookmarkInteractor;
import com.seanschlaefli.cityvenueviewer.data.local.dao.PhotoURLDao;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class PhotoActivityModule {

    @Provides
    public static PhotoPresenter providePhotoPresenter(PhotoView photoView,
                                                       PhotoURLDao photoURLDao,
                                                       BookmarkInteractor bookmarkInteractor) {
        return new PhotoPresenter(photoView, photoURLDao, bookmarkInteractor);
    }
    @Binds
    abstract PhotoView providePhotoView(PhotoActivity photoActivity);
}
