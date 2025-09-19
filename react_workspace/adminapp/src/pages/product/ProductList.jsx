import { useState, useEffect } from "react";
import { getProducts } from "../../utils/ProductApi";
import { Link } from "react-router-dom";

export default function ProductList() {
  const [productList, setProductList] = useState([]);

  const getProductList = async () => {
    console.log("상품 목록 요청할꺼야");
    const response = await getProducts();
    console.log("서버에서 받아온 결과는 :", response.data); // [object Object] 방지
    setProductList(response.data.result || []);
  };

  useEffect(() => {
    getProductList();
  }, []);

  return (
    <div className="row">
      <div className="col-12">
        <div className="card">
          <div className="card-header">
            <h3 className="card-title">Responsive Hover Table</h3>

            <div className="card-tools">
              <div className="input-group input-group-sm" style={{ width: "150px" }}>
                <input type="text" name="table_search" className="form-control float-right" placeholder="Search" />
                <div className="input-group-append">
                  <button type="submit" className="btn btn-default">
                    <i className="fas fa-search"></i>
                  </button>
                </div>
              </div>
            </div>
          </div>

          <div className="card-body table-responsive p-0">
            <table className="table table-hover text-nowrap">
              <thead>
                <tr>
                  <th>No</th>
                  <th>이미지</th>
                  <th>카테고리</th>
                  <th>상품명</th>
                  <th>브랜드</th>
                  <th>가격</th>
                  <th>할인가</th>
                </tr>
              </thead>
              <tbody>
                {productList.map((product, idx) => {
                  const firstFile = product.productFileDTO?.[0]; // ← 안전 접근
                  const filename = firstFile?.filename;
                  const imgSrc = filename
                    ? `http://localhost:7777/productapp/resource/p${product.productId}/${filename}`
                    : "/images/no-image.png"; // placeholder 이미지(없으면 임시로 빈 문자열 "")

                  return (
                    <tr key={product.productId ?? idx}>
                      <td>{product.productId}</td>
                      <td>
                        {filename ? (
                          <img
                            src={imgSrc}
                            alt={product.productName}
                            style={{ width: 64, height: 64, objectFit: "cover" }}
                            onError={(e) => (e.currentTarget.src = "/images/no-image.png")}
                          />
                        ) : (
                          <span className="text-muted">이미지 없음</span>
                        )}
                      </td>
                      <td>{product.subCategoryDTO?.subcategoryName ?? "-"}</td>
                      <td>
                        <Link to={`/product/detail/${product.productId}`}>{product.productName}</Link>
                      </td>
                      <td>{product.brand ?? "-"}</td>
                      <td>{product.price?.toLocaleString?.() ?? "-"}</td>
                      <td>{product.discount ?? 0}%</td>
                    </tr>
                  );
                })}

                <tr>
                  <td colSpan={7}>
                    <button type="button">상품등록</button>
                    <button type="button" onClick={getProductList}>
                      상품목록
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

        </div>
      </div>
    </div>
  );
}
