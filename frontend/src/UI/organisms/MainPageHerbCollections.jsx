import { useState } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import herbCollectionList from "../molecules/HerbCollectionList";
// import FarmCollectionAvatarInfo from "./FarmCollectionAvatarInfo";
import MainHerbAvatarInfo from "./MainHerbAvatarInfo";
import { useGetAllHerbBooksQuery } from "../../services/farm";

function Collections() {
    const isLogin = useSelector((state) => state.user.isLogin);
    const { data } = useGetAllHerbBooksQuery(
        {},
        { skip: !isLogin, refetchOnMountOrArgChange: true }
    );
    const herbname = "프로틴 중독 파슬리";
    const user = useSelector((state) => state.user.user);
    const herbBook = useSelector((state) => state.farm.herbBook);

    return (
        <div className="w-full mb-5 rounded-3xl bg-white drop-shadow px-6 py-5 lg:w-2/3 lg:mb-0">
            <header className="flex justify-between items-center">
                <h3 className="text-lg font-bold">{user?.name} 님의 도감</h3>
                <Link to="/farm">
                    <span className="rounded-full px-2 py-1 text-sm">
                        내 농장 가기
                    </span>
                </Link>
            </header>
            <div className="grid lg:grid-cols-largeCollections items-center justify-center">
                <div className="flex flex-col items-center mt-5">
                    <img
                        className="w-28 h-28"
                        src={user?.herbBookImageUrl}
                        alt="profileHerb"
                    />
                    <div className="my-3 font-semibold">
                        {user?.herbBookName}
                    </div>
                </div>
                <div className="px-4 pt-8 md:px-10 w-full grid grid-cols-5 md:grid-cols-6 lg:grid-cols-8 gap-x-1 gap-y-5 md:gap-x-5 lg:px-0 lg:mx-1 lg:pt-4 lg:gap-y-3">
                    {herbBook?.map((info, idx) => {
                        return (
                            <MainHerbAvatarInfo
                                count={info.count}
                                herbBook={info.herbBook}
                                key={idx}
                            />
                        );
                    })}
                </div>
            </div>
        </div>
    );
}

export default Collections;
